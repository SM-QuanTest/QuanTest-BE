package com.quantest.quantestbe.domain.pattern.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quantest.quantestbe.domain.pattern.dto.PatternResponseDto;
import com.quantest.quantestbe.domain.pattern.entity.Direction;
import com.quantest.quantestbe.domain.pattern.service.PatternService;
import com.quantest.quantestbe.domain.stock.dto.StockResultResponseDto;
import com.quantest.quantestbe.global.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patterns")
public class PatternController {

	private final PatternService patternService;

	// 패턴 다건 조회
	@GetMapping
	public ResponseEntity<Response<List<PatternResponseDto>>> getPatterns(
		@RequestParam(value = "direction", required = false) Direction direction
	) {
		List<PatternResponseDto> res = patternService.getPatterns(direction);
		return ResponseEntity.ok(Response.success("패턴 다건 조회 성공", res));
	}

	// 패턴 탐지된 종목 다건 조회
	@GetMapping("/{patternId}")
	public ResponseEntity<Response<List<StockResultResponseDto>>> getPatternDetectedStocks(
		@PathVariable Long patternId) {
		List<StockResultResponseDto> res = patternService.getPatternDetectedStocks(patternId);
		return ResponseEntity.ok(Response.success("패턴 탐지된 종목 다건 조회 성공", res));
	}

}