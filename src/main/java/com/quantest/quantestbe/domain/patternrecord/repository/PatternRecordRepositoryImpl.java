package com.quantest.quantestbe.domain.patternrecord.repository;

import static com.quantest.quantestbe.domain.chart.entity.QChart.chart;
import static com.quantest.quantestbe.domain.pattern.entity.QPattern.pattern;
import static com.quantest.quantestbe.domain.patternrecord.entity.QPatternRecord.patternRecord;
import static com.quantest.quantestbe.domain.record.entity.QRecord.record;
import static com.quantest.quantestbe.domain.stock.entity.QStock.stock;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.quantest.quantestbe.domain.patternrecord.dto.PatternRecordResponseDto;
import com.quantest.quantestbe.domain.patternrecord.dto.QPatternRecordResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PatternRecordRepositoryImpl implements PatternRecordRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public PatternRecordResponseDto findPatternRecord(Long stockId, Long patternRecordId) {
		PatternRecordResponseDto result = queryFactory
			.select(new QPatternRecordResponseDto(pattern.id, pattern.patternName, record.id, patternRecord.id, patternRecord.patternRecordDate, pattern.patternDirection))
			.from(patternRecord)
			.join(patternRecord.pattern, pattern)
			.join(patternRecord.record, record)
			.join(record.chart, chart)
			.join(chart.stock, stock)
			.where(
				stock.id.eq(stockId),
				patternRecord.id.eq(patternRecordId)
			)
			.fetchOne();

		return result;
	}

	@Override
	public List<PatternRecordResponseDto> findPatternRecords(Long stockId, LocalDate startDate, LocalDate endDate) {
		List<PatternRecordResponseDto> result = queryFactory
			.select(new QPatternRecordResponseDto(pattern.id, pattern.patternName, record.id, patternRecord.id, patternRecord.patternRecordDate, pattern.patternDirection))
			.from(patternRecord)
			.join(patternRecord.pattern, pattern)
			.join(patternRecord.record, record)
			.join(record.chart, chart)
			.join(chart.stock, stock)
			.where(
				stock.id.eq(stockId),
				patternRecord.patternRecordDate.between(startDate, endDate)
			)
			.orderBy(patternRecord.patternRecordDate.desc())
			.fetch();

		return result;
	}

}
