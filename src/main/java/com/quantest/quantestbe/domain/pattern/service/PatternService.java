package com.quantest.quantestbe.domain.pattern.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantest.quantestbe.domain.pattern.Repository.PatternRepository;
import com.quantest.quantestbe.domain.pattern.dto.PatternResponseDto;
import com.quantest.quantestbe.domain.pattern.entity.Direction;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatternService {

	private final PatternRepository patternRepository;

	public List<PatternResponseDto> getPatterns(Direction direction) {
		List<PatternResponseDto> patternResponseDto = patternRepository.findAll()
			.stream()
			.filter(pattern -> {
				if (direction == null)
					return true;
				if (Direction.BEARISH.equals(direction))
					return direction.getPatternDirection().equals(pattern.getPatternDirection());
				if (Direction.BULLISH.equals(direction))
					return direction.getPatternDirection().equals(pattern.getPatternDirection());
				return false;
			})
			.map(pattern -> PatternResponseDto.builder()
				.patternId(pattern.getId())
				.patternName(pattern.getPatternName())
				.patternDirection(pattern.getPatternDirection())
				.build())
			.collect(Collectors.toList());

		return patternResponseDto;
	}

}
