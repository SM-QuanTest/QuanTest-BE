package com.quantest.quantestbe.domain.patternrecord.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantest.quantestbe.domain.patternrecord.dto.PatternRecordResponseDto;
import com.quantest.quantestbe.domain.patternrecord.repository.PatternRecordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatternRecordService {

	private final PatternRecordRepository patternRecordRepository;

	public PatternRecordResponseDto getPatternRecord(Long stockId, Long patternRecordId) {
		return patternRecordRepository.findPatternRecord(stockId, patternRecordId);
	}

	public List<PatternRecordResponseDto> getPatternRecords(Long stockId, LocalDate startDate, LocalDate endDate) {
		return patternRecordRepository.findPatternRecords(stockId, startDate, endDate);
	}
}


