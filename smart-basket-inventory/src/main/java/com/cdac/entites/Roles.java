package com.cdac.entites;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "users")
@Table(name = "role")
public class Roles extends BaseEntity{
	@Enumerated(EnumType.STRING)
	private USER_ROLES  name;
	@ManyToMany
	Set<User> users = new HashSet<>();
}
