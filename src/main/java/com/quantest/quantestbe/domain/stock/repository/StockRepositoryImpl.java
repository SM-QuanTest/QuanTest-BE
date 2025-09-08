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

import com.quantest.quantestbe.domain.chart.dto.ChartSearchRequestDto;
import com.quantest.quantestbe.domain.chart.entity.QChart;
import com.quantest.quantestbe.domain.dailyIndicator.entity.QDailyIndicator;
import com.quantest.quantestbe.domain.indicator.dto.IndicatorLineSearchRequestDto;
import com.quantest.quantestbe.domain.indicator.dto.IndicatorSearchRequestDto;
import com.quantest.quantestbe.domain.search.dto.SearchRequestDto;
import com.quantest.quantestbe.domain.search.entity.FilterType;
import com.quantest.quantestbe.domain.search.entity.Operator;
import com.quantest.quantestbe.domain.stock.dto.QStockResultResponseDto;
import com.quantest.quantestbe.domain.stock.dto.StockResultResponseDto;
import com.quantest.quantestbe.domain.stock.entity.Category;
import com.quantest.quantestbe.global.exception.CustomException;
import com.quantest.quantestbe.global.exception.ErrorCode;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
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

	@Override
	public List<StockResultResponseDto> findSearchResult(LocalDate date, SearchRequestDto searchRequestDto) {

		JPAQuery<StockResultResponseDto> query = queryFactory
			.select(new QStockResultResponseDto(
				stock.id, stock.stockName,
				chart.chartChangePercentage, chart.chartClose,
				record.recordDirection
			))
			.from(chart)
			.join(chart.stock, stock)
			.leftJoin(record).on(record.chart.eq(chart));

		BooleanExpression sectorFiltering = sectorFiltering(searchRequestDto.getSectorIds());
		BooleanExpression chartFiltering = chartFiltering(searchRequestDto.getChartFilterList());
		BooleanExpression indicatorFiltering = indicatorFiltering(searchRequestDto.getIndicatorFilterList(), chart);

		query.where(
			chart.chartDate.eq(date),
			sectorFiltering,
			chartFiltering,
			indicatorFiltering
		);

		return query.fetch();
	}

	private BooleanExpression sectorFiltering(List<Long> sectorIds) {
		if (sectorIds == null || sectorIds.isEmpty()) {
			return null;
		}
		return stock.sector.id.in(sectorIds);
	}

	private BooleanExpression chartFiltering(List<ChartSearchRequestDto> chartFilterList) {
		if (chartFilterList == null || chartFilterList.isEmpty()) {
			return null;
		}

		BooleanExpression result = null;

		for (ChartSearchRequestDto chartSearchRequestDto : chartFilterList) {
			BooleanExpression be = chartPriceTypeFiltering(chartSearchRequestDto);
			if (be != null) {
				if (result == null) {
					result = be;
				} else {
					result = result.and(be);
				}
			}
		}

		return result;

	}

	private BooleanExpression chartPriceTypeFiltering(ChartSearchRequestDto chartSearchRequestDto) {
		if (chartSearchRequestDto == null || chartSearchRequestDto.getPriceType() == null) {
			return null;
		}

		NumberPath<? extends Number> priceType = switch (chartSearchRequestDto.getPriceType()) {
			case CHART_OPEN -> chart.chartOpen;
			case CHART_CLOSE -> chart.chartClose;
			case CHART_HIGH -> chart.chartHigh;
			case CHART_LOW -> chart.chartLow;
			case CHART_VOLUME -> chart.chartVolume;
		};

		Integer priceMin = chartSearchRequestDto.getPriceMin();
		Integer priceMax = chartSearchRequestDto.getPriceMax();

		if (priceMin == null && priceMax == null) {
			return null;
		} else if (priceMin != null && priceMax == null) {
			return priceType.goe(priceMin);
		} else if (priceMin == null && priceMax != null) {
			return priceType.loe(priceMax);
		} else if (priceMin != null && priceMax != null) {
			return priceType.between(priceMin, priceMax);
		}

		return null;
	}

	private BooleanExpression indicatorFiltering(List<IndicatorSearchRequestDto> indicatorFilterList, QChart chart) {
		if (indicatorFilterList == null || indicatorFilterList.isEmpty()) {
			return null;
		}

		BooleanExpression result = null;

		for (IndicatorSearchRequestDto indicatorSearchRequestDto : indicatorFilterList) {

			List<IndicatorLineSearchRequestDto> indicatorLineFilterList = indicatorSearchRequestDto.getIndicatorLineFilterList();
			if (indicatorLineFilterList == null || indicatorLineFilterList.isEmpty()) {
				continue;
			}

			for (IndicatorLineSearchRequestDto indicatorLineSearchRequestDto : indicatorLineFilterList) {
				BooleanExpression be = indicatorLineFilterTypeFiltering(indicatorLineSearchRequestDto, chart);
				if (be != null) {
					if (result == null) {
						result = be;
					} else {
						result = result.and(be);
					}
				}
			}
		}

		return result;
	}

	private BooleanExpression indicatorLineFilterTypeFiltering(IndicatorLineSearchRequestDto indicatorLineSearchRequestDto, QChart chart) {
		if (indicatorLineSearchRequestDto.getFilterType() == null ||
			indicatorLineSearchRequestDto.getIndicatorLineIdA() == null ||
			indicatorLineSearchRequestDto.getOperator() == null) {
			return null;
		}

		boolean isNeedLineB = isNeedB(indicatorLineSearchRequestDto.getFilterType());
		if (isNeedLineB && indicatorLineSearchRequestDto.getIndicatorLineIdB() == null) {
			return null;
		}

		boolean isNeedThreshold = isNeedThreshold(indicatorLineSearchRequestDto.getFilterType());
		if (isNeedThreshold && indicatorLineSearchRequestDto.getThreshold() == null) {
			return null;
		}

		Operator operator = indicatorLineSearchRequestDto.getOperator();
		Long indicatorLindIdA = indicatorLineSearchRequestDto.getIndicatorLineIdA();
		Long indicatorLindIdB = indicatorLineSearchRequestDto.getIndicatorLineIdB();
		Double threshold = indicatorLineSearchRequestDto.getThreshold();

		QDailyIndicator dailyIndicatorLineA = new QDailyIndicator("dailyIndicatorLineA");
		QDailyIndicator dailyIndicatorLineB = new QDailyIndicator("dailyIndicatorLineB");

		BooleanExpression result = null;

		switch (indicatorLineSearchRequestDto.getFilterType()) {
			case COMPARE_INDICATOR -> {
				result = JPAExpressions.selectOne()
					.from(dailyIndicatorLineA, dailyIndicatorLineB)
					.where(
						dailyIndicatorLineA.chart.eq(chart),
						dailyIndicatorLineB.chart.eq(chart),
						dailyIndicatorLineA.indicatorLine.id.eq(indicatorLindIdA),
						dailyIndicatorLineB.indicatorLine.id.eq(indicatorLindIdB),
						operate(dailyIndicatorLineA.dailyIndicatorValue, operator, dailyIndicatorLineB.dailyIndicatorValue)
					)
					.exists();
			}

			case COMPARE_VALUE -> {
				result = JPAExpressions.selectOne()
					.from(dailyIndicatorLineA)
					.where(
						dailyIndicatorLineA.chart.eq(chart),
						dailyIndicatorLineA.indicatorLine.id.eq(indicatorLindIdA),
						operate(dailyIndicatorLineA.dailyIndicatorValue, operator, threshold)
					)
					.exists();
			}

			case DIFFERENCE -> {
				NumberExpression<Double> diff = dailyIndicatorLineA.dailyIndicatorValue.subtract(dailyIndicatorLineB.dailyIndicatorValue).abs();

				result = JPAExpressions.selectOne()
					.from(dailyIndicatorLineA, dailyIndicatorLineB)
					.where(
						dailyIndicatorLineA.chart.eq(chart),
						dailyIndicatorLineB.chart.eq(chart),
						dailyIndicatorLineA.indicatorLine.id.eq(indicatorLindIdA),
						dailyIndicatorLineB.indicatorLine.id.eq(indicatorLindIdB),
						operate(diff, operator, threshold)
					)
					.exists();
			}

			case SPREAD -> {
				// 분모 0 방지
				BooleanExpression safe = dailyIndicatorLineB.dailyIndicatorValue.ne(0.0);
				NumberExpression<Double> spread = dailyIndicatorLineA.dailyIndicatorValue.abs()
					.divide(dailyIndicatorLineB.dailyIndicatorValue.abs())
					.multiply(100.0);

				result = JPAExpressions.selectOne()
					.from(dailyIndicatorLineA, dailyIndicatorLineB)
					.where(
						dailyIndicatorLineA.chart.eq(chart),
						dailyIndicatorLineB.chart.eq(chart),
						dailyIndicatorLineA.indicatorLine.id.eq(indicatorLindIdA),
						dailyIndicatorLineB.indicatorLine.id.eq(indicatorLindIdB),
						safe,
						operate(spread, operator, threshold)
					)
					.exists();
			}

			case RATIO -> {
				// 분모 0 방지
				BooleanExpression safe = dailyIndicatorLineB.dailyIndicatorValue.ne(0.0);
				NumberExpression<Double> ratio = dailyIndicatorLineA.dailyIndicatorValue
					.divide(dailyIndicatorLineB.dailyIndicatorValue)
					.multiply(100.0);

				result = JPAExpressions.selectOne()
					.from(dailyIndicatorLineA, dailyIndicatorLineB)
					.where(
						dailyIndicatorLineA.chart.eq(chart),
						dailyIndicatorLineB.chart.eq(chart),
						dailyIndicatorLineA.indicatorLine.id.eq(indicatorLindIdA),
						dailyIndicatorLineB.indicatorLine.id.eq(indicatorLindIdB),
						safe,
						operate(ratio, operator, threshold)
					)
					.exists();
			}

		}

		return result;
	}

	private boolean isNeedB(FilterType filterType) {

		if (filterType == FilterType.COMPARE_VALUE) {
			return false;
		}
		return true;
	}

	private boolean isNeedThreshold(FilterType filterType) {

		if (filterType == FilterType.COMPARE_INDICATOR) {
			return false;
		}
		return true;

	}

	private BooleanExpression operate(NumberExpression<Double> left, Operator operator, NumberExpression<Double> right) {
		return switch (operator) {
			case GREATER_THAN -> left.gt(right);
			case GREATER_THAN_OR_EQUAL -> left.goe(right);
			case EQUAL -> left.eq(right);
			case LESS_THAN_OR_EQUAL -> left.loe(right);
			case LESS_THAN -> left.lt(right);
		};
	}

	private BooleanExpression operate(NumberExpression<Double> left, Operator operator, Double right) {
		return switch (operator) {
			case GREATER_THAN -> left.gt(right);
			case GREATER_THAN_OR_EQUAL -> left.goe(right);
			case EQUAL -> left.eq(right);
			case LESS_THAN_OR_EQUAL -> left.loe(right);
			case LESS_THAN -> left.lt(right);
		};
	}

}
