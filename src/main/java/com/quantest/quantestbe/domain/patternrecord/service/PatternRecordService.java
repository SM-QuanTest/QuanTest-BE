package com.quantest.quantestbe.domain.patternrecord.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantest.quantestbe.domain.patternrecord.dto.PatternRecordResponseDto;
import com.quantest.quantestbe.domain.patternrecord.repository.PatternRecordRepository;
import com.quantest.quantestbe.global.exception.CustomException;
import com.quantest.quantestbe.global.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatternRecordService {

	private final PatternRecordRepository patternRecordRepository;

	public PatternRecordResponseDto getPattern(Long patternRecordId) {
		if (patternRecordRepository.findById(patternRecordId).isEmpty()) {
			throw new CustomException(ErrorCode.PATTERNRECORD_NOT_FOUND);
		}
		return patternRecordRepository.findByPatternRecordId(patternRecordId);
	}
}
