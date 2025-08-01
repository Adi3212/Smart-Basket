package com.cdac.dto;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto{
	@NotBlank(message = "Name cannot be blank")
	private String name;

	@Email(message = "Invalid email format")
	@NotBlank(message = "Email cannot be blank")
	private String email;

	@NotBlank(message = "Password cannot be blank")
	@Length(min = 6, message = "Minimum 6 characters required")
	private String password;

	@Length(min = 10, max = 10, message = "Phone must be 10 digits")
	private String phone_number;

	private Set<Long> roleIds = new HashSet<>();
}