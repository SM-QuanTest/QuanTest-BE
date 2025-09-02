package com.quantest.quantestbe.domain.pattern.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PatternResponseDto {

	private Long patternId;
	private String patternName;
	private String patternDirection;
	private String patternImageUrl;

}
