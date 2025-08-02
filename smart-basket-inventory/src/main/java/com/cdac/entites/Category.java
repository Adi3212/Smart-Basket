package com.cdac.entites;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Table(name = "category")
@Getter
@Setter
@ToString(callSuper = true)
/* 
 * Field	Type
id	Long (PK)
name	String
items	List<GroceryItem> (1:M)
 * */
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank(message = "category name cannot be blank")
	private String name;
	@ToString.Exclude
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval = true)
	List<GroceryItem> gorcery_items = new ArrayList<>();
}
