package com.quantest.quantestbe.domain.record.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantest.quantestbe.domain.record.entity.PatternRecord;

public interface PatternRecordRepository extends JpaRepository<PatternRecord, Long>, PatternRecordRepositoryCustom {

}
