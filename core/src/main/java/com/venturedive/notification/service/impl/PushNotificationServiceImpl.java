package com.venturedive.notification.service.impl;

import com.venturedive.notification.exception.NotificationServiceException;
import com.venturedive.notification.model.constant.NFLocale;
import com.venturedive.notification.model.constant.NotificationServiceType;
import com.venturedive.notification.model.dto.NotificationDto;
import com.venturedive.notification.model.dto.PushNotificationDto;
import com.venturedive.notification.model.dto.Recipient;
import com.venturedive.notification.model.entity.PushNotificationTemplate;
import com.venturedive.notification.model.map.PushNotificationMap;
import com.venturedive.notification.service.PushNotificationSender;
import com.venturedive.notification.service.PushNotificationTemplateService;
import com.venturedive.notification.util.ObjectUtility;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Log4j2
@Component(NotificationServiceType.PUSH_NOTIFICATION)
public class PushNotificationServiceImpl extends NotificationServiceImpl {

  @Autowired private PushNotificationSender pushNotificationSender;

  @Autowired private PushNotificationTemplateService pushNotificationTemplateService;

  @Override
  public void send(NotificationDto notificationDto) throws NotificationServiceException {
    log.info(String.format("Push Notification of Type %s", notificationDto.getActionType()));
    if (Optional.ofNullable(notificationDto.getLanguageData()).isPresent()
        && !notificationDto.getLanguageData().isEmpty()) {
      sendMultilingualPushNotification(notificationDto);
    } else {
      sendNotification(notificationDto);
    }
  }

  private void sendNotification(NotificationDto notificationDto)
      throws NotificationServiceException {
    PushNotificationTemplate pushNotificationTemplate =
        pushNotificationTemplateService.findByActionType(
            notificationDto.getActionType(), NFLocale.EN_US);
    if (Objects.nonNull(pushNotificationTemplate)) {
      for (Recipient recipient : ObjectUtility.safeList(notificationDto.getRecipients())) {
        PushNotificationDto pushNotificationDto =
            PushNotificationDto.builder()
                .recipient(recipient)
                .data(PushNotificationMap.getPushNotificationDataMap(notificationDto.getData()))
                .receiverType(notificationDto.getActionType().getReceiverType())
                .notificationTitle(pushNotificationTemplate.getTitle())
                .notificationMessage(pushNotificationTemplate.getText())
                .notificationEvent(notificationDto.getActionType().getNotificationEvent())
                .build();
        pushNotificationSender.send(pushNotificationDto);
      }
    }
  }

  private void sendMultilingualPushNotification(NotificationDto notificationDto)
      throws NotificationServiceException {
    for (Recipient recipient : ObjectUtility.safeList(notificationDto.getRecipients())) {
      NFLocale locale = recipient.getLocale();
      PushNotificationTemplate pushNotificationTemplate =
          pushNotificationTemplateService.findByActionType(notificationDto.getActionType(), locale);
      if (Objects.nonNull(pushNotificationTemplate)) {
        PushNotificationDto pushNotificationDto =
            PushNotificationDto.builder()
                .recipient(recipient)
                .data(
                    PushNotificationMap.getPushNotificationDataMap(
                        (Map<String, Object>) notificationDto.getLanguageData().get(locale)))
                .receiverType(notificationDto.getActionType().getReceiverType())
                .notificationTitle(pushNotificationTemplate.getTitle())
                .notificationMessage(pushNotificationTemplate.getText())
                .notificationEvent(notificationDto.getActionType().getNotificationEvent())
                .build();
        pushNotificationSender.send(pushNotificationDto);
      }
    }
  }
}
