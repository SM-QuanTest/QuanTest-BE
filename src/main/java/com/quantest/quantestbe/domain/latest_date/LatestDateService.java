package com.quantest.quantestbe.domain.latest_date;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantest.quantestbe.domain.latest_date.entity.LatestDate;
import com.quantest.quantestbe.domain.latest_date.repository.LatestDateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LatestDateService {

	private final LatestDateRepository latestDateRepository;

	public LocalDate getLatestDateByLatestDateName(String latestDateName) {
		LatestDate latestDate = latestDateRepository.findLatestDateByLatestDateName(latestDateName)
			.orElseThrow();
		return latestDate.getLatestDate();
	}

}
