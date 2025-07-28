package com.cdac.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grocery")

public class GroceryItemController {
	@GetMapping("/grocery")
	public ResponseEntity<?> getAllItem(){
		return null;
	}
	@GetMapping("/grocery/{id}")
	public ResponseEntity<?> getGroceryWithId(@PathVariable long id){
		return null;
	}
	@PostMapping("/grocery")
	public ResponseEntity<?> addGrocery(){
		return null;
	}
	@PutMapping("/grocery/{id}")
	public ResponseEntity<?> updateGrocery(@PathVariable long id){
		return null;
	}
	@DeleteMapping("/grocery/{id}")
	public ResponseEntity<?> deleteGrocery(@PathVariable long id){
		return null;
	}
	
	
	
	
	
}
