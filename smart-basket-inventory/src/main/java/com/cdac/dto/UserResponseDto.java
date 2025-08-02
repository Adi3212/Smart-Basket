package com.cdac.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserResponseDto {
	private String name;
	private String email;
	private String phone_number;

	private Set<RolesDto> roles = new HashSet<>();
	private List<UserGroceryResponseDto> grocery_items = new ArrayList<>();
}
