package com.venturedive.notification.service;

import com.venturedive.notification.exception.NotificationServiceException;
import com.venturedive.notification.model.dto.EmailDto;

/** Created by Safeer Ansari on 9/12/2018. */
public interface EmailSender {
  void sendEmail(EmailDto emailDto) throws NotificationServiceException;
}
