package com.quantest.quantestbe.domain.patternrecord.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantest.quantestbe.domain.patternrecord.entity.PatternRecord;

public interface PatternRecordRepository extends JpaRepository<PatternRecord, Long>, PatternRecordRepositoryCustom {

}
