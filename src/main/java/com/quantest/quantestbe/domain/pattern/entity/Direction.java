package com.quantest.quantestbe.domain.pattern.entity;

public enum Direction {
	BEARISH("상승형"),
	BULLISH("하락형");

	private final String patternDirection;

	Direction(String patternDirection) {
		this.patternDirection = patternDirection;
	}

	public String getPatternDirection() {
		return patternDirection;
	}
}
