package com.cdac.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class GroceryItemDto {
	 private Long id;
	    private String name;
	    private LocalDate expiryDate;
	    private UserDto user;
}
