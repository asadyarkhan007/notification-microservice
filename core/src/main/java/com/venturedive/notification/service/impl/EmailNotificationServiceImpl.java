package com.venturedive.notification.service.impl;

import com.venturedive.notification.exception.NotificationServiceException;
import com.venturedive.notification.model.constant.NFLocale;
import com.venturedive.notification.model.constant.NotificationServiceType;
import com.venturedive.notification.model.dto.EmailDto;
import com.venturedive.notification.model.dto.NotificationDto;
import com.venturedive.notification.model.dto.Recipient;
import com.venturedive.notification.model.entity.EmailTemplate;
import com.venturedive.notification.service.EmailSender;
import com.venturedive.notification.service.EmailTemplateService;
import com.venturedive.notification.util.ObjectUtility;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Log4j2
@Component(NotificationServiceType.EMAIL)
public class EmailNotificationServiceImpl extends NotificationServiceImpl {

  @Autowired private EmailSender emailSender;

  @Autowired private EmailTemplateService emailTemplateService;

  @Override
  public void send(NotificationDto notificationDto) throws NotificationServiceException {
    log.info(String.format("Email Notification of Type %s", notificationDto.getActionType()));
    if (Objects.nonNull(notificationDto.getLanguageData())
        && !notificationDto.getLanguageData().isEmpty()) {
      sendMultilingualEmail(notificationDto);
    } else sendEmail(notificationDto);
  }

  private void sendEmail(NotificationDto notificationDto) throws NotificationServiceException {
    EmailTemplate emailTemplate =
        emailTemplateService.findByActionType(notificationDto.getActionType(), NFLocale.EN_US);
    if (Objects.nonNull(emailTemplate)) {
      for (Recipient recipient : ObjectUtility.safeList(notificationDto.getRecipients())) {
        EmailDto emailDto =
            EmailDto.builder()
                .recipient(recipient)
                .cc(emailTemplate.getCc())
                .bcc(emailTemplate.getBcc())
                .data(notificationDto.getData())
                .emailTemplate(emailTemplate.getContent())
                .build();
        emailSender.sendEmail(emailDto);
      }
    }
  }

  private void sendMultilingualEmail(NotificationDto notificationDto)
      throws NotificationServiceException {
    for (Recipient recipient : ObjectUtility.safeList(notificationDto.getRecipients())) {
      NFLocale locale = recipient.getLocale();
      EmailTemplate emailTemplate =
          emailTemplateService.findByActionType(notificationDto.getActionType(), locale);
      if (Objects.nonNull(emailTemplate)) {
        EmailDto emailDto =
            EmailDto.builder()
                .recipient(recipient)
                .cc(emailTemplate.getCc())
                .bcc(emailTemplate.getBcc())
                .data((Map<String, Object>) notificationDto.getLanguageData().get(locale))
                .emailTemplate(emailTemplate.getContent())
                .build();
        emailSender.sendEmail(emailDto);
      }
    }
  }
}
