package com.cdac.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entites.User;

public interface UserDao extends JpaRepository<User,Long>{

}
