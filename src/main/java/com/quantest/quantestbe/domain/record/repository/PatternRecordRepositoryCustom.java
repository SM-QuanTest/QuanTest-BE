package com.quantest.quantestbe.domain.record.repository;

import java.time.LocalDate;
import java.util.List;

import com.quantest.quantestbe.domain.record.dto.PatternRecordResponseDto;

public interface PatternRecordRepositoryCustom {

	public PatternRecordResponseDto findPatternRecord(Long stockId, Long patternRecordId);
	public List<PatternRecordResponseDto> findPatternRecords(Long stockId, LocalDate startDate, LocalDate endDate);

}
