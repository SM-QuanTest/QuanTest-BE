package com.quantest.quantestbe.domain.dailyIndicator.entity;

import java.time.LocalDateTime;

import com.quantest.quantestbe.domain.chart.entity.Chart;
import com.quantest.quantestbe.domain.indicator.entity.IndicatorLine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "daily_indicators")
public class DailyIndicator {

	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;

	@Column(name = "daily_indicator_value", nullable = false)
	private Double dailyIndicatorValue;

	@Column(name="created_at")
	private LocalDateTime createdAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "indicator_line_id")
	private IndicatorLine indicatorLine;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chart_id")
	private Chart chart;
}
