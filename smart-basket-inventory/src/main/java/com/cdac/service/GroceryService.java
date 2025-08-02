package com.cdac.service;

import java.util.List;

import com.cdac.dto.ApiResponse;
import com.cdac.dto.GroceryRequestDto;
import com.cdac.dto.GroceryResponseDto;
import com.cdac.entites.GroceryItem;

public interface GroceryService {
	List<GroceryResponseDto> getAllGrocery();
	GroceryResponseDto getGroceryById(long id);
	ApiResponse addGrocery(GroceryRequestDto dto);
	ApiResponse deleteGroceryById(long id);
	GroceryResponseDto updateGrocery(long id,GroceryRequestDto dto);
	List<GroceryResponseDto> groceryOfSpecificUser(long userId);
	List<GroceryResponseDto> expirySoonGrocerys();
}
