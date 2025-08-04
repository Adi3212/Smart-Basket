package com.cdac.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.ApiResponse;
import com.cdac.dto.UserRequestDto;
import com.cdac.dto.UserSigninDto;
import com.cdac.service.UserService;
import org.springframework.security.core.Authentication;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Validated
public class UserController {
	private final UserService service;
    @GetMapping
	public ResponseEntity<?> getAllUsers(){
    	try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(service.getAllUserDetails());
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage()));
		}
	}
  

   
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserWithId(@PathVariable long id){
    	try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(service.getUserWithId(id));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage()));
		}
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserWithId(@RequestBody UserRequestDto dto,@PathVariable long id){
    	try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(service.updateUserWithId(dto, id));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage()));
		}
    }
    //Todo: create api for deleteUser add Controller
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id){
    	return null;
    }
    
}
