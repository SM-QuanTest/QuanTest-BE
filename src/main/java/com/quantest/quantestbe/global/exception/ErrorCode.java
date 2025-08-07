package com.quantest.quantestbe.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	STOCK_NOT_FOUND(HttpStatus.NOT_FOUND, "종목을 찾을 수 없습니다."),

	CHART_NOT_FOUND(HttpStatus.NOT_FOUND, "차트를 찾을 수 없습니다."),

	PATTERNRECORD_NOT_FOUND(HttpStatus.NOT_FOUND, "패턴기록을 찾을 수 없습니다."),

	PATTERN_NOT_FOUND(HttpStatus.NOT_FOUND, "패턴을 찾을 수 없습니다."),

	CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "랭킹 카테고리를 찾을 수 없습니다.");

	private final HttpStatus status;
	private final String message;

}
