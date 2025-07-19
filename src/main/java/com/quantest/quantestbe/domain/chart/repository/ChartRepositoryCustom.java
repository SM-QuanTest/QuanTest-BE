package com.quantest.quantestbe.domain.chart.repository;

import java.time.LocalDate;
import java.util.List;

import com.quantest.quantestbe.domain.stock.dto.StockDailyPriceResponseDto;

public interface ChartRepositoryCustom {

	public List<StockDailyPriceResponseDto> getDailyPrice(Long stockId, LocalDate startDate, LocalDate endDate);

}
