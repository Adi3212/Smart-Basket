package com.cdac.dto;

import java.time.LocalDate;

import com.cdac.entites.UNITS;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserGroceryResponseDto {
	private String name;
	private int quantity;
	private UNITS unit;
	private LocalDate purchaseDate;
	private LocalDate expiryDate;

	private CategoryRespDto category;
}
