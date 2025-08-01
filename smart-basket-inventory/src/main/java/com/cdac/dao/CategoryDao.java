package com.cdac.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entites.Category;

public interface CategoryDao  extends JpaRepository<Category, Long>{

}
