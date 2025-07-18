package com.quantest.quantestbe.domain.patternrecord.repository;

import com.quantest.quantestbe.domain.patternrecord.dto.PatternRecordResponseDto;

public interface PatternRecordRepositoryCustom {

	public PatternRecordResponseDto findByPatternRecordId(Long patternRecordId);

}
