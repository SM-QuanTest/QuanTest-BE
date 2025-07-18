package com.quantest.quantestbe.domain.chart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantest.quantestbe.domain.chart.entity.Chart;

public interface ChartRepository extends JpaRepository<Chart, Long> {

}
