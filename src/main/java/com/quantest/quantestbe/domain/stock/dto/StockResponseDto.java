package com.quantest.quantestbe.domain.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class StockResponseDto {
	private Long stockId;
	private String ticker;
	private String stockName;
	private Long sectorId;
	private String sectorLabel;
}
