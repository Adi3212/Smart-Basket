package com.cdac.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.UserDto;
import com.cdac.service.NotificationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/notify")
public class NotificationController {
	private final NotificationService service;
	  @PostMapping("/expiry")
	    public ResponseEntity<?> triggerExpiryAlert() {
	        try {
				return ResponseEntity.status(HttpStatus.OK)
				.body(service.sendExpiryNotifications());
			} catch (Exception e) {
				// TODO: handle exception
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(e.getMessage());
			}
	    }

	    @PostMapping("/welcome")
	    public ResponseEntity<?> welcomeUser(@RequestBody UserDto user) {
	        return null;
	    }
}
