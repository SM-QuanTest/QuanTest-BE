package com.quantest.quantestbe.domain.search.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantest.quantestbe.domain.search.dto.SearchRequestDto;
import com.quantest.quantestbe.domain.stock.dto.StockResultResponseDto;
import com.quantest.quantestbe.domain.stock.service.StockService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

	private final StockService stockService;

	public List<StockResultResponseDto> getSearchResult(LocalDate date, SearchRequestDto searchRequestDto) {
		List<StockResultResponseDto> stockResultResponseDto = stockService.findSearchResult(date, searchRequestDto);
		return stockResultResponseDto;
	}

}
