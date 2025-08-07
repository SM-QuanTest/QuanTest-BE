package com.quantest.quantestbe.domain.stock.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "market_caps")
public class MarketCap {

	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;

	@Column(name = "cap", nullable = false, unique = true)
	private int cap;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_id")
	private Stock stock;
}