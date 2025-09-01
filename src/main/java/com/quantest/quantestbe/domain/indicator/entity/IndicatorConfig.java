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
@Table(name="indicators_configs")
public class IndicatorConfig {

	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;

	@Column(name = "indicator_config_name", nullable = false)
	private String indicatorConfigName;

	@Column(name = "indicator_config_value", nullable = false)
	private String indicatorConfigValue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "indicator_id", nullable = false)
	private Indicator indicator;
}
