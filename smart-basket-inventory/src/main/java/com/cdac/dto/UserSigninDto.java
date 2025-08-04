package com.cdac.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSigninDto {
	private String email;  // or use 'email' based on your app
    private String password;
}
