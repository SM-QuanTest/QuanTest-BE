package com.quantest.quantestbe.domain.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantest.quantestbe.domain.stock.entity.Sector;

public interface SectorRepository extends JpaRepository<Sector, Long> {

}
