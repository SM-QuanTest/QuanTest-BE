package com.quantest.quantestbe.domain.indicator.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.quantest.quantestbe.domain.chart.dto.IndicatorResponseDto;
import com.quantest.quantestbe.domain.indicator.repository.IndicatorRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IndicatorService {

	private final IndicatorRepository indicatorRepository;

	public List<IndicatorResponseDto> getIndicatorList() {
		List<IndicatorResponseDto>  indicatorResponseDtos = indicatorRepository.findAll()
			.stream()
			.map(indicator -> IndicatorResponseDto.builder()
				.indicatorId(indicator.getId())
				.indicatorName(indicator.getIndicatorName())
				.build())
			.collect(Collectors.toList());

		return indicatorResponseDtos;
	}

}
