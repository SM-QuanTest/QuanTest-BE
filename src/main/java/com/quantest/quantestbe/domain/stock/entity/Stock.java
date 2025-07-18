package com.quantest.quantestbe.domain.stock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name="stocks")
public class Stock {

	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;

	@Column(name="ticker", nullable = false, columnDefinition = "VARCHAR(6)")
	private String ticker;

	@Column(name="stock_name", nullable = false)
	private String stockName;

	@ManyToOne
	@JoinColumn(name="sector_id")
	private Sector sector;

}
