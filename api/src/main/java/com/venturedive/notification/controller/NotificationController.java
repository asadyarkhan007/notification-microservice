package com.venturedive.notification.controller;

import com.venturedive.notification.config.SpringApplicationContext;
import com.venturedive.notification.exception.NotificationServiceException;
import com.venturedive.notification.model.constant.NotificationServiceType;
import com.venturedive.notification.model.dto.NotificationDto;
import com.venturedive.notification.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Created by Safeer Ansari on 9/14/2018. */
@RestController
@RequestMapping(value = "notification")
public class NotificationController {

  @PostMapping()
  public void notify(@RequestBody NotificationDto notificationDto) throws NotificationServiceException {


  }



  @PostMapping(path = "/email")
  public void sendEmail(@RequestBody NotificationDto notificationDto) throws NotificationServiceException {
    ((NotificationService) SpringApplicationContext.getBean(NotificationServiceType.EMAIL)).send(notificationDto);
  }

  @PostMapping(path = "/notification")
  public void sendPushNotification(@RequestBody NotificationDto notificationDto)
      throws NotificationServiceException {
    ((NotificationService) SpringApplicationContext.getBean(NotificationServiceType.PUSH_NOTIFICATION))
        .send(notificationDto);
  }

  @PostMapping(path = "/sms")
  public void sendSMS(@RequestBody NotificationDto notificationDto)
      throws NotificationServiceException {
    ((NotificationService) SpringApplicationContext.getBean(NotificationServiceType.SMS))
        .send(notificationDto);
  }
}
