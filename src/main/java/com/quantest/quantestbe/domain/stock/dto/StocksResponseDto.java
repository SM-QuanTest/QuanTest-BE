package com.quantest.quantestbe.domain.stock.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StocksResponseDto {

	Long stockId;
	String stockName;

}
