package com.cdac.entites;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Table(name = "grocery_item")
@Getter
@Setter
@ToString(callSuper = true)
/*
 * Field	Type
id	Long (PK)
name	String
quantity	Integer
unit	String (kg, ltr, etc.)
purchaseDate	Date
expiryDate	Date
user	User (M:1)
category	Category (M:1)
createdAt	Timestamp
updatedAt	Timestamp


 *  */
public class GroceryItem extends BaseEntity{
	@NotBlank(message = "grocery name cannot be blank")
	private String name;
	private int quantity;
	@Enumerated(EnumType.STRING)
	private UNITS unit;
	private LocalDate purchaseDate;
	private LocalDate expiryDate;
	@ManyToOne
	@JoinColumn(name = "category_id")
	@ToString.Exclude
	private Category category;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@ToString.Exclude
	private User user;
	
}
