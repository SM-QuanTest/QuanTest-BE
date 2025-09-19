package com.quantest.quantestbe.domain.chart.repository;

import java.time.LocalDate;
import java.util.List;

import com.quantest.quantestbe.domain.stock.dto.StockDailyPriceResponseDto;

public interface ChartRepositoryCustom {

	public LocalDate getLatestDate(Long stockId);

	public List<StockDailyPriceResponseDto> getDailyPrice(Long stockId, int limit, LocalDate cursorDate);

}
