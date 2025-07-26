package com.quantest.quantestbe.domain.stock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name="sectors")
public class Sector {

	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;

	@Column(name = "sector_name", nullable = false, unique = true)
	private String sectorName;

}