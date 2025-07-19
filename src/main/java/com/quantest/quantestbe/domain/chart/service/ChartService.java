package com.quantest.quantestbe.domain.chart.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.quantest.quantestbe.domain.chart.dto.ChartResponseDto;
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

		Chart chart = chartRepository.findById(stockId)
			.orElseThrow(() -> new CustomException(ErrorCode.STOCK_NOT_FOUND));

		ChartResponseDto chartResponseDto = new ChartResponseDto(
			chart.getId(),
			chart.getChartDate(),
			chart.getChartOpen(),
			chart.getChartHigh(),
			chart.getChartLow(),
			chart.getChartClose(),
			chart.getChartVolume(),
			chart.getChartTurnover(),
			chart.getChartChangePercentage()
		);
		return chartResponseDto;
	}

	public List<StockDailyPriceResponseDto> getDailyPrice(Long stockId, LocalDate startDate, LocalDate endDate) {
		return chartRepository.getDailyPrice(stockId, startDate, endDate);
	}

}
