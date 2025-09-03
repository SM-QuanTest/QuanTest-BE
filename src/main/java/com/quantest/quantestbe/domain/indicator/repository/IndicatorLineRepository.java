package com.quantest.quantestbe.domain.indicator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantest.quantestbe.domain.indicator.entity.IndicatorLine;

public interface IndicatorLineRepository extends JpaRepository<IndicatorLine, Integer> {

	List<IndicatorLine> findAllByIndicatorId(Long indicatorId);

}
