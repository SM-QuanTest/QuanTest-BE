package com.quantest.quantestbe.domain.stock.entity;

public enum Category {
	TURNOVER("거래대금"),
	VOLUME("거래량"),
	RISE("상승"),
	FALL("하락");

	private final String categoryName;

	Category(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}
}
