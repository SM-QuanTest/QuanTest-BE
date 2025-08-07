package com.quantest.quantestbe.domain.pattern.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantest.quantestbe.domain.pattern.entity.Pattern;

public interface PatternRepository extends JpaRepository<Pattern, Long> {
}
