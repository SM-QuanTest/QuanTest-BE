package com.quantest.quantestbe.domain.indicator.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quantest.quantestbe.domain.indicator.dto.IndicatorConfigResponseDto;
import com.quantest.quantestbe.domain.indicator.dto.IndicatorLineResponseDto;
import com.quantest.quantestbe.domain.indicator.dto.IndicatorResponseDto;
import com.quantest.quantestbe.domain.indicator.service.IndicatorService;
import com.quantest.quantestbe.global.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/indicators")
public class IndicatorController {

	private final IndicatorService indicatorService;

	@GetMapping
	public ResponseEntity<Response<List<IndicatorResponseDto>>> getIndicatorList() {
		List<IndicatorResponseDto> res = indicatorService.getIndicatorList();
		return ResponseEntity.ok(Response.success("지표 이름 다건조회 성공", res));
	}

	@GetMapping("/{indicatorId}/configs")
	public ResponseEntity<Response<List<IndicatorConfigResponseDto>>> getIndicatorConfigListByIndicatorId(
		@PathVariable("indicatorId") long indicatorId
	) {
		List<IndicatorConfigResponseDto> res = indicatorService.getIndicatorConfigListByIndicatorId(indicatorId);
		return ResponseEntity.ok(Response.success("지표에 따른 지표 설정 다건조회 성공", res));
	}

	@GetMapping("/{indicatorId}/lines")
	public ResponseEntity<Response<List<IndicatorLineResponseDto>>> getIndicatorLineListByIndicatorId(
		@PathVariable("indicatorId") long indicatorId
	) {
		List<IndicatorLineResponseDto> res = indicatorService.getIndicatorLineListByIndicatorId(indicatorId);
		return ResponseEntity.ok(Response.success("지표에 따른 지표 라인 다건조회 성공", res));

	}
}
