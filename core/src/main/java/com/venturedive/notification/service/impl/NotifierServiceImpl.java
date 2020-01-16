package com.venturedive.notification.service.impl;

import com.venturedive.notification.exception.NotificationServiceException;
import com.venturedive.notification.model.constant.NotificationServiceType;
import com.venturedive.notification.model.dto.NotificationDto;
import com.venturedive.notification.repository.EmailTemplateRepository;
import com.venturedive.notification.repository.PushNotificationTemplateRepository;
import com.venturedive.notification.repository.SMSTemplateRepository;
import com.venturedive.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(NotificationServiceType.ALL)
public class NotifierServiceImpl implements NotificationService {

   @Autowired
   private SMSTemplateRepository smsTemplateRepository;

   @Autowired
   private EmailTemplateRepository emailTemplateRepository;

   @Autowired
   private PushNotificationTemplateRepository pushNotificationTemplateRepository;


    @Override
    public void send(NotificationDto notificationDto) throws NotificationServiceException {

    }
}
