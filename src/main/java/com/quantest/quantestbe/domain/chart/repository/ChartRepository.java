package com.quantest.quantestbe.domain.chart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantest.quantestbe.domain.chart.entity.Chart;

public interface ChartRepository extends JpaRepository<Chart, Long>, ChartRepositoryCustom {

	List<Chart> findTop2ByStockIdOrderByChartDateDesc(Long stockId);

}
