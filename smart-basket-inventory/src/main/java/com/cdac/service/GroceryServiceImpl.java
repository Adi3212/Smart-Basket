package com.cdac.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dao.GroceryDao;
import com.cdac.dto.ApiResponse;
import com.cdac.dto.GroceryResponseDto;
import com.cdac.entites.GroceryItem;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
@Service
@Transactional
@AllArgsConstructor
public class GroceryServiceImpl implements GroceryService{
	
	private  final GroceryDao groceryDao; 
	private final ModelMapper mapper;
	@Override
	public List<GroceryItem> getAllGrocery() {
		// TODO Auto-generated method stub
		
		 return groceryDao.findAll();
		 
	}

	@Override
	public GroceryResponseDto getGroceryById(long id) {
		// TODO Auto-generated method stub
		
		      GroceryItem item=  groceryDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("the data not found with the id"));
		    return mapper.map(item, GroceryResponseDto.class);
		 

	}

	@Override
	public ApiResponse addGrocery(GroceryResponseDto dto) {
		// TODO Auto-generated method stub
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
	public GroceryResponseDto updateGrocery(long id, GroceryResponseDto dto) {
		// TODO Auto-generated method stub
		  groceryDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("no grocery by the id"));
		  
		  mapper.map(dto, GroceryItem.class);
		 
		 return null;
	}

}
