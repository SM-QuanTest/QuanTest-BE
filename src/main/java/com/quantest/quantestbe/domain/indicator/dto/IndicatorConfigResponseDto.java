package com.quantest.quantestbe.domain.indicator.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IndicatorConfigResponseDto {

	private String indicatorConfigName;
	private String indicatorConfigValue;

}
