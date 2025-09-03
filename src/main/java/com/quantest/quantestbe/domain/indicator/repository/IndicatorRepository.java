package com.quantest.quantestbe.domain.indicator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantest.quantestbe.domain.indicator.entity.Indicator;

public interface IndicatorRepository extends JpaRepository<Indicator, Long> {
}
