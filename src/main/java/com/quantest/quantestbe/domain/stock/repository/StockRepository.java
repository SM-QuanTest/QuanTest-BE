package com.quantest.quantestbe.domain.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantest.quantestbe.domain.stock.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long>, StockRepositoryCustom {
}
