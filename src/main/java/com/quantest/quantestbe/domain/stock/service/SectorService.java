package com.quantest.quantestbe.domain.stock.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantest.quantestbe.domain.patternrecord.dto.SectorResponseDto;
import com.quantest.quantestbe.domain.stock.repository.SectorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SectorService {

	private final SectorRepository sectorRepository;

	public List<SectorResponseDto> getSectors() {
		List<SectorResponseDto> sectorResponseDtos = sectorRepository.findAll()
			.stream()
			.map(sector -> SectorResponseDto.builder()
				.SectorId(sector.getId())
				.SectorName(sector.getSectorName())
				.build())
			.collect(Collectors.toList());

		return sectorResponseDtos;
	}

}
