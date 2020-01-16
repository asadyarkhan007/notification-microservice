package com.venturedive.notification.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.venturedive.notification.exception.NotificationServiceException;
import com.venturedive.notification.gateway.EmailGateway;
import com.venturedive.notification.model.dto.EmailDto;
import com.venturedive.notification.model.map.DataMap;
import com.venturedive.notification.service.EmailSender;
import lombok.extern.log4j.Log4j2;
import org.antlr.stringtemplate.StringTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

/** Created by Safeer Ansari on 9/12/2018. */
@Service("email")
@Log4j2
public class EmailSenderImpl implements EmailSender {

  @Autowired private EmailGateway emailGateway;

  @Async
  @Override
  public void sendEmail(EmailDto emailDto) throws NotificationServiceException {
    emailDto.getData().putAll(new ObjectMapper().convertValue(emailDto.getRecipient(), Map.class));
    StringTemplate emailContent =
        DataMap.populateStringTemplate(
            emailDto.getData(), new StringTemplate(emailDto.getEmailTemplate()));
    emailGateway.send(
        emailDto.getEmailSubject(),
        emailContent.toString(),
        emailDto.getRecipient().getRecipientEmail(),
        emailDto.getCc(),
        emailDto.getBcc());
  }
}
