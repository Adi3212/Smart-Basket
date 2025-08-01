package com.cdac.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserMiniDto extends BaseDto {
	private String name;
	private String email;
	private String phone_number;
}

