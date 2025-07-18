package com.quantest.quantestbe.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {

	private final HttpStatus statusCode;
	private final String message;

	public CustomException(ErrorCode errorCode) {
		this.statusCode = errorCode.getStatus();
		this.message = errorCode.getMessage();
	}
}
