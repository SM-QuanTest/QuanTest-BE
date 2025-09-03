package com.quantest.quantestbe.domain.indicator.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IndicatorLineResponseDto {

	private Long indicatorLineId;
	private String indicatorLineName;
}
