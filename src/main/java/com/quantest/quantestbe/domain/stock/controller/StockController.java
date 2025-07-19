package com.quantest.quantestbe.domain.stock.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quantest.quantestbe.domain.stock.dto.StockRankingResponseDto;
import com.quantest.quantestbe.domain.stock.dto.StockResponseDto;
import com.quantest.quantestbe.domain.stock.entity.Category;
import com.quantest.quantestbe.domain.stock.service.StockService;
import com.quantest.quantestbe.global.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {
	private final StockService stockService;

	// 종목 차트 리스트 조회
	@GetMapping("/rankings")
	public ResponseEntity<Response<StockRankingResponseDto>> getStockRanking(
		@RequestParam(defaultValue = "TURNOVER") Category category,
		@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
	) {
		StockRankingResponseDto res = stockService.getStockRanking(category, date);
		return ResponseEntity.ok(Response.success("종목 랭킹 리스트 조회 성공", res));
	}

	// 종목 단건 조회
	@GetMapping("/{stockId}")
	public ResponseEntity<Response<StockResponseDto>> getStock(@PathVariable Long stockId) {
		StockResponseDto res = stockService.getStock(stockId);
		return ResponseEntity.ok(Response.success("종목 정보 단건 조회 성공", res));
	}

}
