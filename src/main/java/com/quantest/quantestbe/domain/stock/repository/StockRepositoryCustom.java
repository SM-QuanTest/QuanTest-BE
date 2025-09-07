package com.quantest.quantestbe.domain.stock.repository;

import java.time.LocalDate;
import java.util.List;

import com.quantest.quantestbe.domain.search.dto.SearchRequestDto;
import com.quantest.quantestbe.domain.stock.dto.StockResultResponseDto;
import com.quantest.quantestbe.domain.stock.entity.Category;

public interface StockRepositoryCustom {

	public List<StockResultResponseDto> findStockRanking(Category category, LocalDate date);

	public List<StockResultResponseDto> findPatternDetectedStocks(Long patternId);

	public List<StockResultResponseDto> findSearchResult(LocalDate date, SearchRequestDto searchRequestDto);
}
