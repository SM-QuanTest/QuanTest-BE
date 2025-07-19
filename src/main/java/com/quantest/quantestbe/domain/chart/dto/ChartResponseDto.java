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
	private LocalDate chartDate;
	private int chartOpen;
	private int chartHigh;
	private int chartLow;
	private int chartClose;
	private Integer chartVolume;
	private Long chartTurnover;
	private Double chartChangePercent;

}
