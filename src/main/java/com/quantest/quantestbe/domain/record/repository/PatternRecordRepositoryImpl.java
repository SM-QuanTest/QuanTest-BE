package com.quantest.quantestbe.domain.record.repository;

import static com.quantest.quantestbe.domain.chart.entity.QChart.chart;
import static com.quantest.quantestbe.domain.pattern.entity.QPattern.pattern;
import static com.quantest.quantestbe.domain.record.entity.QPatternRecord.patternRecord;
import static com.quantest.quantestbe.domain.stock.entity.QStock.stock;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.quantest.quantestbe.domain.record.dto.PatternRecordResponseDto;
import com.quantest.quantestbe.domain.record.dto.QPatternRecordResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PatternRecordRepositoryImpl implements PatternRecordRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public PatternRecordResponseDto findPatternRecord(Long stockId, Long patternRecordId) {
		PatternRecordResponseDto result = queryFactory
			.select(new QPatternRecordResponseDto(pattern.id, pattern.patternName, patternRecord.id, chart.chartDate, pattern.patternDirection))
			.from(patternRecord)
			.join(patternRecord.pattern, pattern)
			.join(patternRecord.chart, chart)
			.join(chart.stock, stock)
			.where(
				stock.id.eq(stockId),
				patternRecord.id.eq(patternRecordId)
			)
			.fetchOne();

		return result;
	}

	@Override
	public List<PatternRecordResponseDto> findPatternRecords(Long stockId, int limit, LocalDate cursorDate) {
		List<PatternRecordResponseDto> result = queryFactory
			.select(new QPatternRecordResponseDto(pattern.id, pattern.patternName, patternRecord.id, chart.chartDate, pattern.patternDirection))
			.from(patternRecord)
			.join(patternRecord.pattern, pattern)
			.join(patternRecord.chart, chart)
			.join(chart.stock, stock)
			.where(
				stock.id.eq(stockId),
				chart.chartDate.loe(cursorDate)
			)
			.orderBy(chart.chartDate.desc())
			.limit(limit)
			.fetch();

		return result;
	}

}
