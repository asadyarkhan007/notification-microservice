package com.venturedive.notification.service.impl;

import com.venturedive.notification.exception.NotificationServiceException;
import com.venturedive.notification.gateway.GenericPushNotificationGateway;
import com.venturedive.notification.model.constant.NFLocale;
import com.venturedive.notification.model.dto.PushNotificationDto;
import com.venturedive.notification.model.dto.pushnotification.base.GenericPushRequestObject;
import com.venturedive.notification.model.dto.pushnotification.base.GenericPushResponseObject;
import com.venturedive.notification.service.PushNotificationSender;
import lombok.extern.log4j.Log4j2;
import org.antlr.stringtemplate.StringTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Created by Safeer Ansari on 9/12/2018. */
@Service
@Log4j2
public class PushNotificationSenderImpl implements PushNotificationSender {

  @Autowired GenericPushNotificationGateway pushNotificationGateway;

  @Value("${push-notification.channel.id}")
  private String pushNotificationChannelId;

  @Override
  public void send(PushNotificationDto pushNotificationDto) throws NotificationServiceException {
    log.info("sending push notification : " + pushNotificationDto);
    StringTemplate messageTemplate =
        new StringTemplate(pushNotificationDto.getNotificationMessage());
    for (Map.Entry pair : pushNotificationDto.getData().entrySet()) {
      messageTemplate.setAttribute((String) pair.getKey(), pair.getValue());
    }
    List<String> deviceUUIds = pushNotificationDto.getRecipient().getPushNotificationId();
    if (!deviceUUIds.isEmpty()) {
      GenericPushRequestObject pushRequestObject =
          new GenericPushRequestObject(
              new ArrayList<>(deviceUUIds),
              pushNotificationDto.getNotificationTitle(),
              messageTemplate.toString(),
              NFLocale.EN_US.getLanguageCode(),
              pushNotificationDto.getData(),
              pushNotificationChannelId);
      GenericPushResponseObject genericPushResponseObject =
          pushNotificationGateway.push(pushRequestObject);
    }
  }
}
