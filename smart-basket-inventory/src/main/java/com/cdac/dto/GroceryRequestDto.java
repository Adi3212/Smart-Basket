package com.cdac.dto;

import java.time.LocalDate;

import com.cdac.entites.Category;
import com.cdac.entites.UNITS;
import com.cdac.entites.User;

public class GroceryRequestDto {
	private String name;
	private int quantity;
	private UNITS unit;
	private LocalDate purchaseDate;
	private LocalDate expiryDate;
	private Category category;
	private User user;
}
