package com.quantest.quantestbe.domain.latest_date.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantest.quantestbe.domain.latest_date.entity.LatestDate;

public interface LatestDateRepository extends JpaRepository<LatestDate, String> {
	Optional<LatestDate> findLatestDateByLatestDateName(String latestDateName);
}

