package com.quantest.quantestbe.domain.record.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quantest.quantestbe.domain.record.dto.PatternRecordCursorResponseDto;
import com.quantest.quantestbe.domain.record.dto.PatternRecordResponseDto;
import com.quantest.quantestbe.domain.record.service.PatternRecordService;
import com.quantest.quantestbe.global.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/charts/{stockId}/patterns")
public class PaternRecordController {

	private final PatternRecordService patternRecordService;

	// @GetMapping("/{patternRecordId}")
	// public ResponseEntity<Response<PatternRecordResponseDto>> getPatternRecord(
	// 	@PathVariable Long stockId,
	// 	@PathVariable Long patternRecordId) {
	// 	PatternRecordResponseDto res = patternRecordService.getPatternRecord(stockId, patternRecordId);
	// 	return ResponseEntity.ok(Response.success("패턴 탐지 단건 조회 성공", res));
	//
	// }

	@GetMapping
	public ResponseEntity<Response<PatternRecordCursorResponseDto>> getPatternRecords(
		@PathVariable Long stockId,
		@RequestParam(defaultValue = "100") int limit,
		@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate cursorDate
	) {
		PatternRecordCursorResponseDto res = patternRecordService.getPatternRecordsCursor(stockId, limit, cursorDate);
		return ResponseEntity.ok(Response.success("패턴 탐지 다건 조회 성공", res));

	}

}
