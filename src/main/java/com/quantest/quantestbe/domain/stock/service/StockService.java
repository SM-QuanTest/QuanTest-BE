package com.quantest.quantestbe.domain.stock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantest.quantestbe.domain.stock.dto.StockResponseDto;
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

}