package com.cdac.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entities.Notification;

public interface NotificationDao extends JpaRepository<Notification, Long> {

}
