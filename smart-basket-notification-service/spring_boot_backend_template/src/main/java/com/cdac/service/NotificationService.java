package com.cdac.service;

import com.cdac.Response.ApiResponse;
import com.cdac.dto.UserDto;

public interface NotificationService {
	 ApiResponse sendExpiryNotifications();
}
