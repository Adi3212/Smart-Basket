package com.cdac.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cdac.entites.GroceryItem;
import com.cdac.entites.User;

public interface UserDao extends JpaRepository<User,Long>{
	@Query("SELECT u FROM User u JOIN FETCH u.grocery_items JOIN FETCH u.roles")
	List<User> findAllWithCatAndGroc();
}
