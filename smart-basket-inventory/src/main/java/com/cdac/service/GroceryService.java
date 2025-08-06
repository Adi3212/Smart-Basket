package com.cdac.service;

import java.util.List;

import com.cdac.dto.ApiResponse;
import com.cdac.dto.GroceryRequestDto;
import com.cdac.dto.GroceryResponseDto;
import com.cdac.entites.GroceryItem;

public interface GroceryService {
	List<GroceryResponseDto> getAllGrocery();
	GroceryResponseDto getGroceryById(Long id);
	ApiResponse addGrocery(GroceryRequestDto dto);
	ApiResponse deleteGroceryById(Long id);
	GroceryResponseDto updateGrocery(Long id,GroceryRequestDto dto);
	List<GroceryResponseDto> groceryOfSpecificUser(Long userId);
	List<GroceryResponseDto> expirySoonGrocerys();
}
