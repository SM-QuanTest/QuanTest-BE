package com.quantest.quantestbe.domain.search.dto;

import java.util.List;

import com.quantest.quantestbe.domain.chart.dto.ChartSearchRequestDto;
import com.quantest.quantestbe.domain.indicator.dto.IndicatorSearchRequestDto;

import lombok.Getter;

@Getter
public class SearchRequestDto {
	List<Long> sectorIds;
	List<ChartSearchRequestDto> chartFilterList;
	List<IndicatorSearchRequestDto> indicatorFilterList;
}
