package com.quantest.quantestbe.domain.stock.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quantest.quantestbe.domain.stock.dto.SectorResponseDto;
import com.quantest.quantestbe.domain.stock.service.SectorService;
import com.quantest.quantestbe.global.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sectors")
public class SectorController {

	private final SectorService sectorService;

	// 업종 다건 조회
	@GetMapping
	public ResponseEntity<Response<List<SectorResponseDto>>> getSectors(
	) {
		List<SectorResponseDto> res = sectorService.getSectors();
		return ResponseEntity.ok(Response.success("업종 다건 조회 성공", res));
	}

}
