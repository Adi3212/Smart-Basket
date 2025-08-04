package com.cdac.entites;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/* 
 * Field	Type
id	Long (PK)
name	String
email	String (Unique)
password	String
phoneNumber	String
createdAt	Timestamp
updatedAt	Timestamp
roles	Set<Role> (M:M)
groceryItems	List<GroceryItem> (1:M)
 * */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
@ToString(callSuper = true)
public class User  extends BaseEntity implements UserDetails{
	
	@NotBlank(message = "name cannot be blank")
	private String name;
	@Email(message = "Invalid email format")
	@NotBlank(message = "Email cannot be empty")
	@Column(unique = true)
	private String email;
	@NotBlank(message = "Password cannot be blank")
	@Length(min = 6,message = "min length require is 6")
	private String password;
	@Length(min = 10,max = 10 , message = "phone must be 10 digits")
	private String phone_number;
	@ManyToMany
	@JoinTable(
	        name = "user_roles",  //  Join table name
	        joinColumns = @JoinColumn(name = "user_id"),  // FK to User
	        inverseJoinColumns = @JoinColumn(name = "role_id") // FK to Role
	    )
	 private Set<Roles> roles = new HashSet<>();
	@ToString.Exclude
	 @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
	 List<GroceryItem> grocery_items = new ArrayList<>();
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    for (Roles role : roles) {
	        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
	    }
	    return authorities;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
}
