package com.quantest.quantestbe.domain.record.entity;

import java.time.LocalDateTime;

import com.quantest.quantestbe.domain.chart.entity.Chart;
import com.quantest.quantestbe.domain.pattern.entity.Pattern;

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
@Table(name = "pattern_records")
public class PatternRecord {

	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pattern_id")
	private Pattern pattern;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chart_id")
	private Chart chart;

}
