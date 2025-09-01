package com.quantest.quantestbe.domain.indicator.entity;

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
@Table(name="indicators_lines")
public class IndicatorLine {

	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;

	@Column(name = "indicator_line_name", nullable = false)
	private String indicatorLineName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "indicator_id")
	private Indicator indicator;
}
