package com.quantest.quantestbe.domain.stock.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quantest.quantestbe.domain.stock.dto.StockRankingResponseDto;
import com.quantest.quantestbe.domain.stock.dto.StockResponseDto;
import com.quantest.quantestbe.domain.stock.service.StockService;
import com.quantest.quantestbe.global.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {
	private final StockService stockService;

	// 종목 차트 리스트 조회
	// dto 수정 필요
	@GetMapping("/rankings")
	public ResponseEntity<Response<List<StockRankingResponseDto>>> getChartStock(
		@RequestParam(defaultValue = "turnover") String categoryCode
	) {
		// Category category =
		// List<StockResponseDto> res = stockService.getChartStock(category);
		List<StockRankingResponseDto> res = new ArrayList<StockRankingResponseDto>();
		return ResponseEntity.ok(Response.success("종목 랭킹 리스트 조회 성공", res));
	}


	// 종목 단건 조회
	@GetMapping("/{stockId}")
	public ResponseEntity<Response<StockResponseDto>> getStock(@PathVariable Long stockId) {
		StockResponseDto res = stockService.getStock(stockId);
		return ResponseEntity.ok(Response.success("종목 차트 조회 성공", res));
	}

	/*
	// 종목 일봉가격 조회 - 스크롤 기반 페이지네이션
	@GetMapping("/{ticker}/prices")
	public ResponseEntity<Response<Slice<StockDailyPriceResponseDto>>> getDailyPrice(
		@PathVariable String ticker,
		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate cursorDate,
		@RequestParam int size,
		@RequestParam(defaultValue = "forward") String direction
	) {
		// Slice<StockDailyPrice> slice = stockService.getPage(ticker, cursorDate, size, direction);
		// List<StockDailyPriceResponseDto> res = slice.getContent().stream().map(StockDailyPriceResponseDto::new).toList();
		// String start =  res.get(0).date().toString();
		// String end = res.get(res.size()-1).date().toString();
		//PageInfo pi = new PageInfo( ~~ );
		// Pageable pg =
		// List<StockResponseDto> res = stockService.getDailyPrice(ticker, startDate, endDate);
		Slice<StockDailyPriceResponseDto> ress = res.stream().map(StockDailyPriceResponseDto::new).toList();
		List<>
		return ResponseEntity.ok(Response.success("종목 차트 리스트 조회 성공", res));
	}

	*/
}
