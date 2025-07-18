package com.quantest.quantestbe.domain.patternrecord.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quantest.quantestbe.domain.patternrecord.dto.PatternRecordResponseDto;
import com.quantest.quantestbe.domain.patternrecord.service.PatternRecordService;
import com.quantest.quantestbe.global.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/charts/{stockId}/pattern")
public class PaternRecordController {

	private final PatternRecordService patternRecordService;

	@GetMapping("/{patternRecordId}")
	public ResponseEntity<Response<PatternRecordResponseDto>> getPattern(
		@PathVariable Long stockId,
		@PathVariable Long patternRecordId) {
		PatternRecordResponseDto res = patternRecordService.getPattern(patternRecordId);
		return ResponseEntity.ok(Response.success("패턴 탐지 단건 조회 성공", res));

	}

}
