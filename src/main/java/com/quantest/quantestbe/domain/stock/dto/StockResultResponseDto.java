package com.quantest.quantestbe.domain.stock.dto;

import java.math.BigDecimal;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
public class StockResultResponseDto {
	private Long stockId;
	private String stockName;
	private BigDecimal chartChangePercentage;
	private int chartClose;
	private char recordDirection;

	@QueryProjection
	public StockResultResponseDto(Long stockId, String stockName, BigDecimal chartChangePercentage, int chartClose, char recordDirection) {
		this.stockId = stockId;
		this.stockName = stockName;
		this.chartChangePercentage = chartChangePercentage;
		this.chartClose = chartClose;
		this.recordDirection = recordDirection;
	}
}
