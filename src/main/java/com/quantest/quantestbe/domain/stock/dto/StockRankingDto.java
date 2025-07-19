package com.quantest.quantestbe.domain.stock.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
public class StockRankingDto {
	private Long stockId;
	private String stockName;
	private double chartChangePercentage;
	private int chartClose;

	@QueryProjection
	public StockRankingDto(Long stockId, String stockName, double chartChangePercentage, int chartClose) {
		this.stockId = stockId;
		this.stockName = stockName;
		this.chartChangePercentage = chartChangePercentage;
		this.chartClose = chartClose;
	}
}
