package com.quantest.quantestbe.domain.chart.dto;

import com.quantest.quantestbe.domain.chart.entity.PriceType;

import lombok.Getter;

@Getter
public class ChartSearchRequestDto {
	/*
	CHART_OPEN,
	CHART_CLOSE,
	CHART_HIGH,
	CHART_LOW,
	CHART_VOLUME
	*/
	PriceType priceType;
	Integer priceMin;
	Integer priceMax;
}
