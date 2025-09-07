package com.quantest.quantestbe.domain.search.entity;

public enum FilterType {
	COMPARE_INDICATOR, // 지표 간 비교 // indicatorLineIdA (operator) indicatorLineIdB
	COMPARE_VALUE, // 지표와 숫자값 간 비교 indicatorLineIdA (operator) threshold // -> indicatorLineIdB 사용X
	DIFFERENCE, // 차이 // indicatorLineIdA - indicatorLineIdB (operator) threshold // 절대값 기준
	SPREAD, // 스프레드 // (indicatorLineIdA - indicatorLineIdB / indicatorLineIdB) * 100 (operator) threshold // 퍼센트
	RATIO; // 비율 // (indicatorLineIdA / indicatorLineIdB) * 100 (operator) threshold // 퍼센트
}
