package com.quantest.quantestbe.domain.stock.dto;

import com.quantest.quantestbe.domain.stock.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockRankingResponseDto {
	private String stockId;
	private String stockName;
	private Category category;
	private String categoryLabel;
	private int close;
	private double changePercentage;
	// private String icon;

}
