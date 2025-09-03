package com.quantest.quantestbe.domain.indicator.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IndicatorResponseDto {

	private Long indicatorId;
	private String indicatorName;
}
