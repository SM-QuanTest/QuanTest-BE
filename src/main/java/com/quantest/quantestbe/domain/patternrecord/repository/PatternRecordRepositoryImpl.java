package com.quantest.quantestbe.domain.patternrecord.repository;

import static com.quantest.quantestbe.domain.pattern.entity.QPattern.pattern;
import static com.quantest.quantestbe.domain.record.entity.QRecord.record;
import static com.quantest.quantestbe.domain.patternrecord.entity.QPatternRecord.patternRecord;

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
	public PatternRecordResponseDto findByPatternRecordId(Long patternRecordId) {
		PatternRecordResponseDto result = queryFactory
			.select(new QPatternRecordResponseDto(pattern.id, pattern.patternName, record.id, patternRecord.id, patternRecord.patternRecordDate, pattern.patternDirection))
			.from(patternRecord)
			.join(pattern).on(patternRecord.pattern.id.eq(pattern.id))
			.join(record).on(patternRecord.record.id.eq(record.id))
			.where(patternRecord.id.eq(patternRecordId))
			.fetchOne();

		return result;
		// return null;
	}

}
