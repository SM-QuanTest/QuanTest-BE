package com.quantest.quantestbe.domain.patternrecord.dto;

import java.time.LocalDate;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
public class PatternRecordResponseDto {

	Long patternId;
	String patternName;
	Long recordId;
	Long patternRecordId;
	LocalDate patternRecordDate;
	String patternDirection;

	@QueryProjection
	public PatternRecordResponseDto(Long patternId, String patternName, Long recordId, Long patternRecordId, LocalDate PatternRecordDate, String patternDirection) {
		this.patternId = patternId;
		this.patternName = patternName;
		this.recordId = recordId;
		this.patternRecordId = patternRecordId;
		this.patternRecordDate = PatternRecordDate;
		this.patternDirection = patternDirection;

	}

}