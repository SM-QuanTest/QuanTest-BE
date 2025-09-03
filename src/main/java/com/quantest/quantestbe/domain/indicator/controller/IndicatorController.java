package com.quantest.quantestbe.domain.indicator.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quantest.quantestbe.domain.chart.dto.IndicatorResponseDto;
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
}
