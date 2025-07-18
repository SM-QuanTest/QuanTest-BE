package com.quantest.quantestbe.domain.chart.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChartResponseDto {

	private long stockId;
	private LocalDate date;
	private int open;
	private int high;
	private int low;
	private int close;
	private Integer volume;
	private Long turnover;
	private Double changePercent;

}
