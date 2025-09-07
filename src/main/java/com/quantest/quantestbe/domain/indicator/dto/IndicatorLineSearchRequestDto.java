package com.quantest.quantestbe.domain.indicator.dto;

import com.quantest.quantestbe.domain.search.entity.FilterType;
import com.quantest.quantestbe.domain.search.entity.Operator;

import lombok.Getter;

@Getter
public class IndicatorLineSearchRequestDto {

	FilterType filterType;
	Long indicatorLineIdA;
	Long indicatorLineIdB;
	Operator operator;
	Double threshold;

}
