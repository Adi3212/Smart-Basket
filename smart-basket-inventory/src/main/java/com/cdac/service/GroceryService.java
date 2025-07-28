package com.cdac.service;

import java.util.List;

import com.cdac.dto.ApiResponse;
import com.cdac.dto.GroceryResponseDto;
import com.cdac.entites.GroceryItem;

public interface GroceryService {
	List<GroceryItem> getAllGrocery();
	GroceryResponseDto getGroceryById(long id);
	ApiResponse addGrocery(GroceryResponseDto dto);
	ApiResponse deleteGroceryById(long id);
	GroceryResponseDto updateGrocery(long id,GroceryResponseDto dto);
	
}
