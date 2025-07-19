package com.quantest.quantestbe.domain.patternrecord.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quantest.quantestbe.domain.patternrecord.dto.PatternRecordResponseDto;
import com.quantest.quantestbe.domain.patternrecord.service.PatternRecordService;
import com.quantest.quantestbe.global.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/charts/{stockId}/patterns")
public class PaternRecordController {

	private final PatternRecordService patternRecordService;

	@GetMapping("/{patternRecordId}")
	public ResponseEntity<Response<PatternRecordResponseDto>> getPatternRecord(
		@PathVariable Long stockId,
		@PathVariable Long patternRecordId) {
		PatternRecordResponseDto res = patternRecordService.getPatternRecord(stockId, patternRecordId);
		return ResponseEntity.ok(Response.success("패턴 탐지 단건 조회 성공", res));

	}

	@GetMapping
	public ResponseEntity<Response<List<PatternRecordResponseDto>>> getPatternRecords(
		@PathVariable Long stockId,
		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		List<PatternRecordResponseDto> res = patternRecordService.getPatternRecords(stockId, startDate, endDate);
		return ResponseEntity.ok(Response.success("패턴 탐지 다건 조회 성공", res));

	}

}
