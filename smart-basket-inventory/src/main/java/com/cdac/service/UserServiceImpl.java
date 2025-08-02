package com.cdac.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dao.GroceryDao;
import com.cdac.dao.UserDao;
import com.cdac.dto.ApiResponse;
import com.cdac.dto.GroceryRequestDto;
import com.cdac.dto.GroceryResponseDto;
import com.cdac.dto.UserGroceryResponseDto;
import com.cdac.dto.UserRequestDto;
import com.cdac.dto.UserResponseDto;
import com.cdac.entites.GroceryItem;
import com.cdac.entites.User;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserDao userDao;
	private ModelMapper mapper;
	private final GroceryDao groceryDao;
	@Override
	public List<UserResponseDto> getAllUserDetails() {
		// TODO Auto-generated method stub
		
		List<User> lst = userDao.findAllWithCatAndGroc();
		List<UserResponseDto> userDto =  lst.stream()
		.map(item -> mapper.map(item, UserResponseDto.class))
		.collect(Collectors.toList());
		
		return userDto;
	}

	@Override
	public UserResponseDto getUserWithId(long id) {
		// TODO Auto-generated method stub
		User user = userDao.findById(id)
			    .orElseThrow(() -> new ResourceNotFoundException("No user with id"));
		 List<UserGroceryResponseDto>dto=user.getGrocery_items()
		.stream()
		.map(item -> mapper.map(item, UserGroceryResponseDto.class))
		.collect(Collectors.toList());
			
		 UserResponseDto userDto = mapper.map(user, UserResponseDto.class);
		 userDto.setGrocery_items(dto);
		 return userDto;
	}

	@Override
	public ApiResponse addUser(UserRequestDto dto) {
		// TODO Auto-generated method stub
		User user = mapper.map(dto, User.class);
		User u = userDao.save(user);
		if(u == null) {
			return new ApiResponse("user not created");
		}
		return new  ApiResponse("user created successfully");
	}

	@Override
	public ApiResponse deleteUser(long id) {
		// TODO Auto-generated method stub
		User user = userDao.findById(id)
			    .orElseThrow(() -> new ResourceNotFoundException("No user with id"));
		
		userDao.delete(user);
		return new ApiResponse("user deleted success");
		
		
		
	}

	@Override
	public UserResponseDto updateUserWithId(UserRequestDto dto, long id) {
		// TODO Auto-generated method stub
		User user = userDao.findById(id)
			    .orElseThrow(() -> new ResourceNotFoundException("No user with id"));
		
		User updatedUser = mapper.map(dto, User.class);
		userDao.save(updatedUser);
		return mapper.map(updatedUser, UserResponseDto.class);
	
	}

}
