package com.quantest.quantestbe.domain.record.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantest.quantestbe.domain.chart.service.ChartService;
import com.quantest.quantestbe.domain.record.dto.PatternRecordCursorResponseDto;
import com.quantest.quantestbe.domain.record.dto.PatternRecordResponseDto;
import com.quantest.quantestbe.domain.record.repository.PatternRecordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatternRecordService {

	private final PatternRecordRepository patternRecordRepository;
	private final ChartService chartService;

	public PatternRecordResponseDto getPatternRecord(Long stockId, Long patternRecordId) {
		return patternRecordRepository.findPatternRecord(stockId, patternRecordId);
	}

	public PatternRecordCursorResponseDto getPatternRecordsCursor(Long stockId, int limit, LocalDate cursorDate) {

		List<PatternRecordResponseDto> patternRecord = getPatternRecords(stockId, limit + 1, cursorDate);

		boolean hasNext = true;
		if (patternRecord.size() <= limit) {
			hasNext = false;
		}

		LocalDate nextCursor = null;

		if (hasNext) {
			nextCursor = patternRecord.get(patternRecord.size() - 1).getPatternRecordDate();
			patternRecord.remove(patternRecord.size() - 1);
		}

		return PatternRecordCursorResponseDto.builder()
			.contents(patternRecord)
			.nextCursor(nextCursor)
			.hasNext(hasNext)
			.build();
	}

	public List<PatternRecordResponseDto> getPatternRecords(Long stockId, int limit, LocalDate cursorDate) {

		if (cursorDate == null) {
			LocalDate latestDate = chartService.getLatestDate(stockId);
			if (latestDate == null) {
				return Collections.emptyList();
			}
			cursorDate = latestDate;
		}

		return patternRecordRepository.findPatternRecords(stockId, limit, cursorDate);
	}
}


