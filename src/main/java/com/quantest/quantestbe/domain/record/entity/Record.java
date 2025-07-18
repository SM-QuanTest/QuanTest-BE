package com.quantest.quantestbe.domain.record.entity;

import java.time.LocalDateTime;

import com.quantest.quantestbe.domain.chart.entity.Chart;

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
@Table(name = "records")
public class Record {

	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;

	@Column(name = "record_direction")
	private char recordDirection;

	@Column(name = "record_prediction")
	private double recordPrediction;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chart_id")
	private Chart chart;

}
