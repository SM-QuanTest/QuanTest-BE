package com.quantest.quantestbe.domain.pattern.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name="patterns")
public class Pattern {

	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;

	@Column(name="pattern_name", nullable=false)
	private String patternName;

	@Column(name="pattern_direction")
	private String patternDirection;
}

