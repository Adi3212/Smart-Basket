package com.cdac.service;

import java.time.LocalDate;
import java.util.ArrayList;
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
	public GroceryResponseDto getGroceryById(Long id) {
		// TODO Auto-generated method stub
		
		      GroceryItem item=  groceryDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("the data not found with the id"));
		    return mapper.map(item, GroceryResponseDto.class);
		 

	}

	@Override
	public ApiResponse addGrocery(GroceryRequestDto dto) {
		// TODO Auto-generated method stub
		System.out.println(dto);
		Category c = categoryDao.findById(dto.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("not category with this id"));
		User u = userDao.findById(dto.getUserId()).orElseThrow(()-> new ResourceNotFoundException("no user with this id"));
		
		GroceryItem item =   mapper.map(dto, GroceryItem.class);
		u.getGrocery_items().add(item); 
		item.setCategory(c);
		item.setUser(u);
		groceryDao.save(item);
		
		
		return new ApiResponse("grocery product added");
	}

	@Override
	public ApiResponse deleteGroceryById(Long id) {
		// TODO Auto-generated method stub
		
		GroceryItem item = groceryDao.findById(id).orElseThrow(()->new ResourceNotFoundException("No grocery with this id"));
		groceryDao.delete(item);
		return new ApiResponse("grocery item deleted");
	}

	@Override
	public GroceryResponseDto updateGrocery(Long id, GroceryRequestDto dto) {

	    
	    GroceryItem existingItem = groceryDao.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("No grocery by the ID"));

	    
	    Category category = categoryDao.findById(dto.getCategoryId())
	        .orElseThrow(() -> new ResourceNotFoundException("No category by the ID"));

	    User user = userDao.findById(dto.getUserId())
	        .orElseThrow(() -> new ResourceNotFoundException("No user by the ID"));

	    mapper.map(dto, existingItem);  
	    
	    existingItem.setCategory(category);
	    existingItem.setUser(user);

	    GroceryItem updated = groceryDao.save(existingItem);

	    
	    return mapper.map(updated, GroceryResponseDto.class);
	}

	@Override
	public List<GroceryResponseDto> groceryOfSpecificUser(Long userId) {
		// TODO Auto-generated method stub
		System.out.println("reached method--------");
		System.out.println(userId);
		
		List<GroceryItem> userItems = groceryDao.findByUserId(userId);
		
		
		return userItems.stream()
				.map(item -> mapper.map(item, GroceryResponseDto.class))
				.collect(Collectors.toList());
		
	}

	@Override
	public List<GroceryResponseDto> expirySoonGrocerys() {
		// TODO Auto-generated method stub
		
		List<GroceryItem> gcList = groceryDao.findAll();
		List<GroceryItem> expiredGroceryItems = new ArrayList<>();
		LocalDate today = LocalDate.now();
		LocalDate thresholdDate = today.plusDays(7); 

		List<GroceryItem> expiringSoonItems = gcList.stream()
			.filter(item -> {
				LocalDate expiry = item.getExpiryDate();
				return expiry != null && !expiry.isBefore(today) && !expiry.isAfter(thresholdDate);
			})
			.collect(Collectors.toList());
		return expiringSoonItems.stream()
				.map(item -> mapper.map(item, GroceryResponseDto.class))
				.collect(Collectors.toList());
	}


}
