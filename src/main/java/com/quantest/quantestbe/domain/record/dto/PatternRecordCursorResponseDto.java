package com.quantest.quantestbe.domain.record.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PatternRecordCursorResponseDto {

	private List<PatternRecordResponseDto> contents;
	private LocalDate nextCursor;
	private boolean hasNext;
}
