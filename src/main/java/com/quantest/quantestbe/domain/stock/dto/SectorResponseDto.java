package com.quantest.quantestbe.domain.stock.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SectorResponseDto {

	Long SectorId;
	String SectorName;

}
