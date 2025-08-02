package com.cdac.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdac.entites.GroceryItem;

public interface GroceryDao extends JpaRepository<GroceryItem,Long>{
	@Query("SELECT g FROM GroceryItem g JOIN FETCH g.user JOIN FETCH g.category")
	List<GroceryItem> findAllWithUserAndCategory();
	@Query("SELECT g FROM GroceryItem g WHERE g.user.id = :id")
	List<GroceryItem> findByUserId(@Param("id") long id);
	
	
}
