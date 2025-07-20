package com.quantest.quantestbe.domain.chart.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ChartResponseDto {

	private Long stockId;
	private LocalDate chartDate;
	private int chartOpen;
	private int chartHigh;
	private int chartLow;
	private int chartClose;
	private Integer chartVolume;
	private Long chartTurnover;
	private Double chartChangePercent;
	private String stockName;
	private int priceChange;

	// public ChartResponseDto(Long stockId, LocalDate chartDate, int chartOpen, int chartHigh, int chartLow, int chartClose, Integer chartVolume, Long chartTurnover, Double chartChangePercent, String stockName, int priceChange) {
	// 	this.stockId = stockId;
	// 	this.chartDate = chartDate;
	// 	this.chartOpen = chartOpen;
	// 	this.chartHigh = chartHigh;
	// 	this.chartLow = chartLow;
	// 	this.chartClose = chartClose;
	// 	this.chartVolume = chartVolume;
	// 	this.chartTurnover = chartTurnover;
	// 	this.chartChangePercent = chartChangePercent;
	// 	this.stockName = stockName;
	// 	this.priceChange = priceChange;
	// }

}
