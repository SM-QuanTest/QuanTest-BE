package com.quantest.quantestbe.domain.stock.dto;

import java.time.LocalDate;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
public class StockDailyPriceResponseDto {

	private Long chartId;
	private LocalDate chartDate;
	private int chartOpen;
	private int chartHigh;
	private int chartLow;
	private int chartClose;
	private int chartVolume;

	@QueryProjection
	public StockDailyPriceResponseDto(Long chartId, LocalDate chartDate, int chartOpen, int chartHigh, int chartLow, int chartClose, int chartVolume) {
		this.chartId = chartId;
		this.chartDate = chartDate;
		this.chartOpen = chartOpen;
		this.chartHigh = chartHigh;
		this.chartLow = chartLow;
		this.chartClose = chartClose;
		this.chartVolume = chartVolume;
	}

}
