package com.quantest.quantestbe.domain.record.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantest.quantestbe.domain.record.entity.Record;

public interface RecordRepository extends JpaRepository<Record, Long> {

}
