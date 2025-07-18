package com.quantest.quantestbe.domain.chart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quantest.quantestbe.domain.chart.dto.ChartResponseDto;
import com.quantest.quantestbe.domain.chart.service.ChartService;
import com.quantest.quantestbe.global.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/charts")
public class ChartController {

	private final ChartService chartService;

	@GetMapping("/{stockId}/latest")
	public ResponseEntity<Response<ChartResponseDto>> getLatestChart(
		@PathVariable("stockId") long stockId
	) {
		ChartResponseDto res = chartService.getLatestChart(stockId);
		return ResponseEntity.ok(Response.success("종목 일봉 차트 단건 조회 성공", res));

	}


}
