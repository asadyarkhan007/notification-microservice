package com.venturedive.notification.service;

import com.venturedive.notification.exception.NotificationServiceException;
import com.venturedive.notification.model.dto.NotificationDto;

public interface NotificationService {
  void send(NotificationDto notificationDto) throws NotificationServiceException;
}
