package com.quantest.quantestbe.domain.indicator.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.quantest.quantestbe.domain.indicator.dto.IndicatorConfigResponseDto;
import com.quantest.quantestbe.domain.indicator.dto.IndicatorLineResponseDto;
import com.quantest.quantestbe.domain.indicator.dto.IndicatorResponseDto;
import com.quantest.quantestbe.domain.indicator.repository.IndicatorConfigRepository;
import com.quantest.quantestbe.domain.indicator.repository.IndicatorLineRepository;
import com.quantest.quantestbe.domain.indicator.repository.IndicatorRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IndicatorService {

	private final IndicatorRepository indicatorRepository;
	private final IndicatorConfigRepository indicatorConfigRepository;
	private final IndicatorLineRepository indicatorLineRepository;

	public List<IndicatorResponseDto> getIndicatorList() {
		List<IndicatorResponseDto> indicatorResponseDtos = indicatorRepository.findAll()
			.stream()
			.map(indicator -> IndicatorResponseDto.builder()
				.indicatorId(indicator.getId())
				.indicatorName(indicator.getIndicatorName())
				.build())
			.collect(Collectors.toList());

		return indicatorResponseDtos;
	}

	public List<IndicatorConfigResponseDto> getIndicatorConfigListByIndicatorId(long indicatorId) {
		List<IndicatorConfigResponseDto> indicatorConfigResponseDtos = indicatorConfigRepository.findAllByIndicatorId(indicatorId)
			.stream()
			.map(indicatorConfig -> IndicatorConfigResponseDto.builder()
				.indicatorConfigName(indicatorConfig.getIndicatorConfigName())
				.indicatorConfigValue(indicatorConfig.getIndicatorConfigValue())
				.build())
			.collect(Collectors.toList());

		return indicatorConfigResponseDtos;
	}

	public List<IndicatorLineResponseDto> getIndicatorLineListByIndicatorId(long indicatorId) {
		List<IndicatorLineResponseDto> indicatorLineResponseDtos = indicatorLineRepository.findAllByIndicatorId(indicatorId)
			.stream()
			.map(indicatorLine -> IndicatorLineResponseDto.builder()
				.indicatorLineId(indicatorLine.getId())
				.indicatorLineName(indicatorLine.getIndicatorLineName())
				.build())
			.collect(Collectors.toList());

		return indicatorLineResponseDtos;
	}

}
