package com.cdac;



import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cdac.dto.CategoryRespDto;
import com.cdac.dto.GroceryResponseDto;
import com.cdac.dto.UserMiniDto;
import com.cdac.entites.Category;
import com.cdac.entites.GroceryItem;
import com.cdac.entites.User;

@SpringBootApplication // includes @Configuration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/*
	 * Configure ModelMapper as spring bean - so thar SC will manage it's life cycle
	 * + provide it as the depcy
	 */
	@Bean // method level annotation - to tell SC , following method
	// rets an object - which has to be managed as a spring bean
	// manages - life cycle +
	public ModelMapper modelMapper() {
	    System.out.println("in model mapper creation");
	    ModelMapper mapper = new ModelMapper();

	    mapper.getConfiguration()
	            .setMatchingStrategy(MatchingStrategies.STRICT)
	            .setPropertyCondition(Conditions.isNotNull());

	   
	  

	    return mapper;
	}

}
