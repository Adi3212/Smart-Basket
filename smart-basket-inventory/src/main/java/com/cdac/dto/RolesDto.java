package com.cdac.dto;

import java.util.HashSet;
import java.util.Set;

import com.cdac.entites.USER_ROLES;
import com.cdac.entites.User;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RolesDto extends BaseDto{
	
	private USER_ROLES  name;
	
	
}

