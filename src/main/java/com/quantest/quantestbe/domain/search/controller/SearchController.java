package com.quantest.quantestbe.domain.search.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quantest.quantestbe.domain.search.dto.SearchRequestDto;
import com.quantest.quantestbe.domain.search.service.SearchService;
import com.quantest.quantestbe.domain.stock.dto.StockResultResponseDto;
import com.quantest.quantestbe.global.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

	private final SearchService searchService;

	@PostMapping
	public ResponseEntity<Response<List<StockResultResponseDto>>> getSearchResult(
		@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
		@RequestBody SearchRequestDto requestDto
	) {
		List<StockResultResponseDto> res = searchService.getSearchResult(date, requestDto);
		return ResponseEntity.ok(Response.success("필터링된 종목 다건 조회 성공", res));
	}
}
