package com.quantest.quantestbe.domain.stock.repository;

import static com.quantest.quantestbe.domain.chart.entity.QChart.chart;
import static com.quantest.quantestbe.domain.pattern.entity.QPattern.pattern;
import static com.quantest.quantestbe.domain.patternrecord.entity.QPatternRecord.patternRecord;
import static com.quantest.quantestbe.domain.record.entity.QRecord.record;
import static com.quantest.quantestbe.domain.stock.entity.QStock.stock;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.quantest.quantestbe.domain.stock.dto.QStockResultResponseDto;
import com.quantest.quantestbe.domain.stock.dto.StockResultResponseDto;
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
	public List<StockResultResponseDto> findStockRanking(Category category, LocalDate date) {

		OrderSpecifier rankingOrder;

		switch (category) {
			case TURNOVER -> rankingOrder = chart.chartTurnover.desc();
			case VOLUME -> rankingOrder = chart.chartVolume.desc();
			case RISE -> rankingOrder = chart.chartChangePercentage.desc();
			case FALL -> rankingOrder = chart.chartChangePercentage.asc();
			default -> throw new CustomException(ErrorCode.CATEGORY_NOT_FOUND);
		}

		List<StockResultResponseDto> result = queryFactory
			.select(new QStockResultResponseDto(
				stock.id, stock.stockName,
				chart.chartChangePercentage, chart.chartClose,
				record.recordDirection
			))
			.from(chart)
			.join(chart.stock, stock)
			.leftJoin(record).on(record.chart.eq(chart))
			.where(chart.chartDate.eq(date))
			.orderBy(rankingOrder)
			.limit(100)
			.fetch();

		return result;
	}

	@Override
	public List<StockResultResponseDto> findPatternDetectedStocks(Long patternId) {

		LocalDate latestDate = queryFactory
			.select(chart.chartDate.max())
			.from(chart)
			.fetchOne();

		if (latestDate == null) {
			return Collections.emptyList();
		}

		List<StockResultResponseDto> result = queryFactory
			.select(new QStockResultResponseDto(
				stock.id, stock.stockName,
				chart.chartChangePercentage, chart.chartClose,
				record.recordDirection
			))
			.from(patternRecord)
			.join(patternRecord.pattern, pattern)
			.join(patternRecord.record, record)
			.join(record.chart, chart)
			.join(chart.stock, stock)
			.where(
				pattern.id.eq(patternId),
				chart.chartDate.eq(latestDate)
			)
			.fetch();

		return result;
	}

}
