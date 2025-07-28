package com.cdac.dto;

import java.time.LocalDate;

import com.cdac.entites.Category;
import com.cdac.entites.UNITS;
import com.cdac.entites.User;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class GroceryResponseDto extends BaseDto {
	private String name;
	private int quantity;
	private UNITS unit;
	private LocalDate purchaseDate;
	private LocalDate expiryDate;
	private Category category;
	private User user;
}
