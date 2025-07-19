package com.quantest.quantestbe.domain.patternrecord.repository;

import java.time.LocalDate;
import java.util.List;

import com.quantest.quantestbe.domain.patternrecord.dto.PatternRecordResponseDto;

public interface PatternRecordRepositoryCustom {

	public PatternRecordResponseDto findPatternRecord(Long stockId, Long patternRecordId);
	public List<PatternRecordResponseDto> findPatternRecords(Long stockId, LocalDate startDate, LocalDate endDate);

}
