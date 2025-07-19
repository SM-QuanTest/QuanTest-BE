package com.quantest.quantestbe.domain.stock.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class StockRankingResponseDto {
	private String categoryName;
	private List<StockRankingDto> stocks;

}
