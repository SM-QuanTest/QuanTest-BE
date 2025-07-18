package com.quantest.quantestbe.global.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonErrorResponse {

	private String message;
	private String error;
	private int statusCode;
	private LocalDateTime timestamp;

}
