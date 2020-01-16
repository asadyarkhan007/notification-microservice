package com.venturedive.notification.service;

import com.venturedive.notification.exception.NotificationServiceException;
import com.venturedive.notification.model.dto.PushNotificationDto;

/** Created by Safeer Ansari on 9/12/2018. */
public interface PushNotificationSender {
  void send(PushNotificationDto pushNotificationDto) throws NotificationServiceException;
}
