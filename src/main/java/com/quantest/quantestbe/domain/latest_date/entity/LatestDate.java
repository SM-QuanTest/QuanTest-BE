package com.quantest.quantestbe.domain.latest_date.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "latest_date")
public class LatestDate {

	@Id
	@Column(name = "latest_date_name", nullable = false)
	private String latestDateName;

	@Column(name = "latest_date")
	private LocalDate latestDate;
}

