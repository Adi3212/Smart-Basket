package com.cdac.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cdac.Response.ApiResponse;
import com.cdac.dao.NotificationDao;
import com.cdac.dto.GroceryItemDto;
import com.cdac.dto.UserDto;
import com.cdac.entities.Notification;
import com.cdac.entities.Status;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
@Service
@Transactional
@AllArgsConstructor
public class ServiceImpl implements NotificationService{

	 
	    private final RestTemplate restTemplate;

	    
	    private final NotificationDao notificationRepo;

	    
	    private final EmailService emailService;

	    @Override
	    public ApiResponse sendExpiryNotifications() {
	        String url = "http://localhost:8080/grocery/expiry-soon";

	        ResponseEntity<List<GroceryItemDto>> response = restTemplate.exchange(
	            url,
	            HttpMethod.GET,
	            null,
	            new ParameterizedTypeReference<List<GroceryItemDto>>() {}
	        );

	        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
	            List<GroceryItemDto> items = response.getBody();

	            for (GroceryItemDto item : items) {
	                try {
	                    String message = "Reminder: '" + item.getName() + "' is expiring on " + item.getExpiryDate();

	                    // Replace with item.getUser().getEmail() once integrated
	                    emailService.sendMail("adityaandhale1048@gmail.com", "Grocery Expiry Reminder", message);

	                    Notification notif = new Notification();
	                    notif.setUserId(item.getUser().getId());
	                    notif.setGroceryItemId(item.getId());
	                    notif.setMessage(message);
	                    notif.setSentTime(LocalDateTime.now());
	                    notif.setStatus(Status.SENT);
	                    notificationRepo.save(notif);

	                } catch (Exception e) {
	                    Notification notif = new Notification();
	                    notif.setUserId(item.getUser().getId());
	                    notif.setGroceryItemId(item.getId());
	                    notif.setMessage("Failed to send: " + e.getMessage());
	                    notif.setSentTime(LocalDateTime.now());
	                    notif.setStatus(Status.FAILED);
	                    notificationRepo.save(notif);
	                }
	            }

	            return new ApiResponse("Notifications processed for all expiry items.");
	        }

	        return new ApiResponse("There is some error fetching expiry items.");
	    }

		
	}






