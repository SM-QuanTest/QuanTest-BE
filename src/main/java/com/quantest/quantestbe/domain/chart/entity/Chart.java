package com.quantest.quantestbe.domain.chart.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name="charts")
public class Chart {

	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;

	@Column(name ="chart_date", nullable = false)
	private LocalDate chartDate;

	@Column(name="chart_open", nullable = false)
	private int chartOpen;

	@Column(name="chart_high", nullable = false)
	private int chartHigh;

	@Column(name="chart_low", nullable = false)
	private int chartLow;

	@Column(name="chart_close", nullable = false)
	private int chartClose;

	@Column(name="chart_Volume")
	private Integer chartVolume;

	@Column(name="chart_turnover")
	private Long chartTurnover;

	@Column(name="chart_change_percentage")
	private Double chartChangePercentage;

	@Column(name="created_at")
	private LocalDateTime createdAt;

}