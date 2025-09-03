package com.quantest.quantestbe.domain.indicator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantest.quantestbe.domain.indicator.entity.IndicatorConfig;

public interface IndicatorConfigRepository extends JpaRepository<IndicatorConfig, Long> {

	List<IndicatorConfig> findAllByIndicatorId(Long indicatorId);

}
