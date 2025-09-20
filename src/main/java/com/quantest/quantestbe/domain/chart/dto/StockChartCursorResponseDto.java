package com.quantest.quantestbe.domain.chart.dto;

import java.time.LocalDate;
import java.util.List;

import com.quantest.quantestbe.domain.stock.dto.StockDailyPriceResponseDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StockChartCursorResponseDto {

	private List<StockDailyPriceResponseDto> contents;
	private LocalDate nextCursor;
	private boolean hasNext;

}
