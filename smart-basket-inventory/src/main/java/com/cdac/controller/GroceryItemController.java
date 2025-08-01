package com.cdac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.ApiResponse;
import com.cdac.dto.GroceryRequestDto;
import com.cdac.service.GroceryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/grocery")
@AllArgsConstructor
public class GroceryItemController {
	
	private  final GroceryService service;
	@GetMapping("/grocery")
	public ResponseEntity<?> getAllItem(){
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(service.getAllGrocery());
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	@GetMapping("/grocery/{id}")
	public ResponseEntity<?> getGroceryWithId(@PathVariable long id){
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(service.getGroceryById(id));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	@PostMapping("/grocery")
	public ResponseEntity<?> addGrocery(@org.springframework.web.bind.annotation.RequestBody GroceryRequestDto dto){
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(service.addGrocery(dto));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}	}
	@PutMapping("/grocery/{id}")
	public ResponseEntity<?> updateGrocery(@org.springframework.web.bind.annotation.RequestBody GroceryRequestDto dto,@ PathVariable long id){
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(service.updateGrocery(id, dto));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	@DeleteMapping("/grocery/{id}")
	public ResponseEntity<?> deleteGrocery(@PathVariable long id){
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(service.deleteGroceryById(id));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	
	
	
	
}
