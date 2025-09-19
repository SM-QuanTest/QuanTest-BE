package com.quantest.quantestbe.domain.chart.repository;

import static com.quantest.quantestbe.domain.chart.entity.QChart.chart;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.quantest.quantestbe.domain.stock.dto.QStockDailyPriceResponseDto;
import com.quantest.quantestbe.domain.stock.dto.StockDailyPriceResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ChartRepositoryImpl implements ChartRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public LocalDate getLatestDate(Long stockId) {
		LocalDate latestDate = queryFactory
			.select(chart.chartDate.max())
			.from(chart)
			.where(chart.stock.id.eq(stockId))
			.fetchOne();

		return latestDate;
	}

	@Override
	public List<StockDailyPriceResponseDto> getDailyPrice(Long stockId, int limit, LocalDate cursorDate) {

		List<StockDailyPriceResponseDto> result = queryFactory
			.select(new QStockDailyPriceResponseDto(chart.id, chart.chartDate, chart.chartOpen, chart.chartHigh, chart.chartLow, chart.chartClose, chart.chartVolume))
			.from(chart)
			.where(
				chart.stock.id.eq(stockId),
				chart.chartDate.loe(cursorDate)
			)
			.orderBy(chart.chartDate.desc())
			.limit(limit)
			.fetch();

		return result;
	}
}
