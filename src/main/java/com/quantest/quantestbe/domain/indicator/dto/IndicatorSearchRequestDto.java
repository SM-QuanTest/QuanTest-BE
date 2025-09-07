package com.quantest.quantestbe.domain.indicator.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class IndicatorSearchRequestDto {
	Long indicatorId;
	List<IndicatorLineSearchRequestDto> indicatorLineFilterList;
}
