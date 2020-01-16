package com.venturedive.notification.service.impl;

import com.venturedive.notification.exception.NotificationServiceException;
import com.venturedive.notification.exception.SMSException;
import com.venturedive.notification.model.constant.NFLocale;
import com.venturedive.notification.model.constant.NotificationServiceType;
import com.venturedive.notification.model.dto.NotificationDto;
import com.venturedive.notification.model.dto.Recipient;
import com.venturedive.notification.model.dto.SmsDto;
import com.venturedive.notification.model.entity.SMSTemplate;
import com.venturedive.notification.model.map.SmsNotificationMap;
import com.venturedive.notification.service.SMSTemplateService;
import com.venturedive.notification.service.SmsSender;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Log4j2
@Component(NotificationServiceType.SMS)
public class SMSNotificationServiceImpl extends NotificationServiceImpl {

  @Autowired private SmsSender smsSender;

  @Autowired private SMSTemplateService smsTemplateService;

  @Override
  public void send(NotificationDto notificationDto) throws NotificationServiceException {
    log.info(String.format("Sms Notification of Type %s", notificationDto.getActionType()));
    if (Optional.ofNullable(notificationDto.getLanguageData()).isPresent()
        && !notificationDto.getLanguageData().isEmpty()) sendMultilingualSms(notificationDto);
    else sendSms(notificationDto);
  }

  private void sendSms(NotificationDto notificationDto) throws SMSException {
    SMSTemplate smsTemplate =
        smsTemplateService.findByActionType(notificationDto.getActionType(), NFLocale.EN_US);
    if (Objects.nonNull(smsTemplate)) {
      for (Recipient recipient : notificationDto.getRecipients()) {
        SmsDto smsDto =
            SmsDto.builder()
                .recipient(recipient)
                .data(SmsNotificationMap.getSmsNotificationDataMap(notificationDto.getData()))
                .notificationMessage(smsTemplate.getText())
                .build();
        smsSender.sendSMS(smsDto);
      }
    }
  }

  private void sendMultilingualSms(NotificationDto notificationDto) throws SMSException {
    for (Recipient recipient : notificationDto.getRecipients()) {
      NFLocale locale = recipient.getLocale();
      SMSTemplate smsTemplate =
          smsTemplateService.findByActionType(notificationDto.getActionType(), locale);
      if (Optional.ofNullable(smsTemplate).isPresent()) {
        SmsDto smsDto =
            SmsDto.builder()
                .recipient(recipient)
                .data(
                    SmsNotificationMap.getSmsNotificationDataMap(
                        (Map<String, Object>) notificationDto.getLanguageData().get(locale)))
                .notificationMessage(smsTemplate.getText())
                .build();
        smsSender.sendSMS(smsDto);
      }
    }
  }
}
