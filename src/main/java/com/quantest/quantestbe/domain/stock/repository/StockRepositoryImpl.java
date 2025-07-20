package com.quantest.quantestbe.domain.stock.repository;

import static com.quantest.quantestbe.domain.chart.entity.QChart.chart;
import static com.quantest.quantestbe.domain.record.entity.QRecord.record;
import static com.quantest.quantestbe.domain.stock.entity.QStock.stock;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.quantest.quantestbe.domain.stock.dto.QStockRankingDto;
import com.quantest.quantestbe.domain.stock.dto.StockRankingDto;
import com.quantest.quantestbe.domain.stock.entity.Category;
import com.quantest.quantestbe.global.exception.CustomException;
import com.quantest.quantestbe.global.exception.ErrorCode;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StockRepositoryImpl implements StockRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<StockRankingDto> findStockRanking(Category category, LocalDate date) {

		OrderSpecifier rankingOrder;

		switch (category) {
			case TURNOVER -> rankingOrder = chart.chartTurnover.desc();
			case VOLUME -> rankingOrder = chart.chartVolume.desc();
			case RISE -> rankingOrder = chart.chartChangePercentage.desc();
			case FALL -> rankingOrder = chart.chartChangePercentage.asc();
			default -> throw new CustomException(ErrorCode.CATEGORY_NOT_FOUND);
		}

		List<StockRankingDto> result = queryFactory
			.select(new QStockRankingDto(stock.id, stock.stockName, chart.chartChangePercentage, chart.chartClose, record.recordDirection))
			.from(record)
			.join(record.chart, chart)
			.join(chart.stock, stock)
			.where(chart.chartDate.eq(date))
			.orderBy(rankingOrder)
			.limit(100)
			.fetch();

		return result;
	}

}
