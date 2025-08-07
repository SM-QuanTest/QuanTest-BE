package com.quantest.quantestbe.domain.stock.repository;

import java.time.LocalDate;
import java.util.List;

import com.quantest.quantestbe.domain.stock.dto.StockRankingDto;
import com.quantest.quantestbe.domain.stock.entity.Category;

public interface StockRepositoryCustom {

	public List<StockRankingDto> findStockRanking(Category category, LocalDate date);

	public List<StockRankingDto> findPatternDetectedStocks(Long patternId);
}
