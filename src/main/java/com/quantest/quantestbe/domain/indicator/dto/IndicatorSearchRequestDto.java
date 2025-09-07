package com.quantest.quantestbe.domain.indicator.dto;

import lombok.Getter;

@Getter
public class IndicatorSearchRequestDto {
	Long indicatorId;
	IndicatorLineSearchRequestDto indicatorLineFilterList;
}
