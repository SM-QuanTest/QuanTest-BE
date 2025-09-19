package com.quantest.quantestbe.domain.chart.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.quantest.quantestbe.domain.chart.dto.ChartResponseDto;
import com.quantest.quantestbe.domain.chart.dto.StockChartResponseDto;
import com.quantest.quantestbe.domain.chart.entity.Chart;
import com.quantest.quantestbe.domain.chart.repository.ChartRepository;
import com.quantest.quantestbe.domain.stock.dto.StockDailyPriceResponseDto;
import com.quantest.quantestbe.global.exception.CustomException;
import com.quantest.quantestbe.global.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChartService {

	private final ChartRepository chartRepository;

	public ChartResponseDto getLatestChart(long stockId) {

		List<Chart> charts = chartRepository
			.findTop2ByStockIdOrderByChartDateDesc(stockId);

		if (charts.isEmpty()) {
			throw new CustomException(ErrorCode.CHART_NOT_FOUND);
		}

		Chart chart = charts.get(0);
		int priceChange = chart.getChartClose();
		if (charts.size() > 1) {
			priceChange -= charts.get(1).getChartClose();
		}

		ChartResponseDto chartResponseDto = ChartResponseDto.builder()
			.stockId(chart.getStock().getId())
			.chartDate(chart.getChartDate())
			.chartOpen(chart.getChartOpen())
			.chartHigh(chart.getChartHigh())
			.chartLow(chart.getChartLow())
			.chartClose(chart.getChartClose())
			.chartVolume(chart.getChartVolume())
			.chartTurnover(chart.getChartTurnover())
			.chartChangePercent(chart.getChartChangePercentage())
			.stockName(chart.getStock().getStockName())
			.priceChange(priceChange)
			.build();

		return chartResponseDto;
	}

	public StockChartResponseDto getStockChart(Long stockId, int limit, LocalDate cursorDate) {

		List<StockDailyPriceResponseDto> dailyPrice = getDailyPrice(stockId, limit + 1, cursorDate);

		boolean hasNext = true;
		if (dailyPrice.size() <= limit) {
			hasNext = false;
		}

		LocalDate nextCursor = null;

		Collections.reverse(dailyPrice);

		if (hasNext) {
			nextCursor = dailyPrice.get(0).getChartDate();
			dailyPrice.remove(0);
		}

		return StockChartResponseDto.builder()
			.contents(dailyPrice)
			.nextCursor(nextCursor)
			.hasNext(hasNext)
			.build();
	}

	public List<StockDailyPriceResponseDto> getDailyPrice(Long stockId, int limit, LocalDate cursorDate) {

		if (cursorDate == null) {
			LocalDate latestDate = chartRepository.getLatestDate(stockId);
			if (latestDate == null) {
				return Collections.emptyList();
			}
			cursorDate = latestDate;
		}

		return chartRepository.getDailyPrice(stockId, limit, cursorDate);
	}

}
