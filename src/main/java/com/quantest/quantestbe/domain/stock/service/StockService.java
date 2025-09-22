package com.quantest.quantestbe.domain.stock.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantest.quantestbe.domain.latest_date.LatestDateService;
import com.quantest.quantestbe.domain.search.dto.SearchRequestDto;
import com.quantest.quantestbe.domain.stock.dto.StockRankingResponseDto;
import com.quantest.quantestbe.domain.stock.dto.StockResponseDto;
import com.quantest.quantestbe.domain.stock.dto.StockResultResponseDto;
import com.quantest.quantestbe.domain.stock.dto.StocksResponseDto;
import com.quantest.quantestbe.domain.stock.entity.Category;
import com.quantest.quantestbe.domain.stock.entity.Stock;
import com.quantest.quantestbe.domain.stock.repository.SectorRepository;
import com.quantest.quantestbe.domain.stock.repository.StockRepository;
import com.quantest.quantestbe.global.exception.CustomException;
import com.quantest.quantestbe.global.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockService {

	private final StockRepository stockRepository;
	private final SectorRepository sectorRepository;
	private final LatestDateService latestDateService;

	public StockResponseDto getStock(Long stockId) {
		Stock stock = stockRepository.findById(stockId)
			.orElseThrow(() -> new CustomException(ErrorCode.STOCK_NOT_FOUND));

		StockResponseDto stockResponseDto = StockResponseDto.builder()
			.stockId(stock.getId())
			.ticker(stock.getTicker())
			.stockName(stock.getStockName())
			.sectorId(stock.getSector() != null ? stock.getSector().getId() : null)
			.sectorLabel(stock.getSector() != null ? stock.getSector().getSectorName() : null)
			.build();

		return stockResponseDto;
	}

	public StockRankingResponseDto getStockRanking(Category category, LocalDate date) {

		LocalDate latestDate = latestDateService.getLatestDateByLatestDateName("charts");
		if (date.isAfter(latestDate)) {
			date = latestDate;
		}

		List<StockResultResponseDto> stockResultResponseDto = stockRepository.findStockRanking(category, date);

		return StockRankingResponseDto.builder()
			.categoryName(category.getCategoryName())
			.chartDate(date)
			.stocks(stockResultResponseDto)
			.build();
	}

	public List<StockResultResponseDto> getPatternDetectedStocks(Long patternId) {
		List<StockResultResponseDto> stockResultResponseDto = stockRepository.findPatternDetectedStocks(patternId);
		return stockResultResponseDto;
	}

	public List<StockResultResponseDto> findSearchResult(LocalDate date, SearchRequestDto searchRequestDto) {
		List<StockResultResponseDto> stockResultResponseDto = stockRepository.findSearchResult(date, searchRequestDto);
		return stockResultResponseDto;
	}

	public List<StocksResponseDto> getStocks() {
		List<StocksResponseDto> stocksResponseDtos = stockRepository.findAll()
			.stream()
			.map(stock -> StocksResponseDto.builder()
				.stockId(stock.getId())
				.stockName(stock.getStockName())
				.build())
			.collect(Collectors.toList());

		return stocksResponseDtos;
	}
}