package com.cdac.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dao.CategoryDao;
import com.cdac.dao.GroceryDao;
import com.cdac.dao.UserDao;
import com.cdac.dto.ApiResponse;
import com.cdac.dto.GroceryRequestDto;
import com.cdac.dto.GroceryResponseDto;
import com.cdac.entites.Category;
import com.cdac.entites.GroceryItem;
import com.cdac.entites.User;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
@Service
@Transactional
@AllArgsConstructor
public class GroceryServiceImpl implements GroceryService{
	
	private  final GroceryDao groceryDao; 
	private final CategoryDao categoryDao;
	private final UserDao userDao;
	private final ModelMapper mapper;
	@Override
	public List<GroceryResponseDto> getAllGrocery() {
		// TODO Auto-generated method stub
		
		  List<GroceryItem> lst=  groceryDao.findAllWithUserAndCategory();
		return lst.stream()
		.map(item -> mapper.map(item, GroceryResponseDto.class))
		.collect(Collectors.toList());
	}

	@Override
	public GroceryResponseDto getGroceryById(long id) {
		// TODO Auto-generated method stub
		
		      GroceryItem item=  groceryDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("the data not found with the id"));
		    return mapper.map(item, GroceryResponseDto.class);
		 

	}

	@Override
	public ApiResponse addGrocery(GroceryRequestDto dto) {
		// TODO Auto-generated method stub
		System.out.println(dto);
		GroceryItem item =   mapper.map(dto, GroceryItem.class);
		
		groceryDao.save(item);
		
		
		return new ApiResponse("grocery product added");
	}

	@Override
	public ApiResponse deleteGroceryById(long id) {
		// TODO Auto-generated method stub
		
		GroceryItem item = groceryDao.findById(id).orElseThrow(()->new ResourceNotFoundException("No grocery with this id"));
		groceryDao.delete(item);
		return new ApiResponse("grocery item deleted");
	}

	@Override
	public GroceryResponseDto updateGrocery(long id, GroceryRequestDto dto) {

	    // 1. Pehle existing grocery item fetch karo
	    GroceryItem existingItem = groceryDao.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("No grocery by the ID"));

	    // 2. Related entities fetch karo
	    Category category = categoryDao.findById(dto.getCategoryId())
	        .orElseThrow(() -> new ResourceNotFoundException("No category by the ID"));

	    User user = userDao.findById(dto.getUserId())
	        .orElseThrow(() -> new ResourceNotFoundException("No user by the ID"));

	    // 3. DTO ke fields ko existing entity me map karo
	    mapper.map(dto, existingItem);  // ye same object update karega, naye object nahi banayega

	    // 4. Manually set karo user aur category, kyunki wo id se map nahi hote
	    existingItem.setCategory(category);
	    existingItem.setUser(user);

	    // 5. Save the updated entity
	    GroceryItem updated = groceryDao.save(existingItem);

	    // 6. Convert entity → response DTO
	    return mapper.map(updated, GroceryResponseDto.class);
	}


}
