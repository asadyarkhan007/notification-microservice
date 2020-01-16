package com.venturedive.notification.service.impl;

import com.venturedive.notification.exception.SMSException;
import com.venturedive.notification.gateway.SMSGateway;
import com.venturedive.notification.model.dto.SmsDto;
import com.venturedive.notification.service.SmsSender;
import lombok.extern.log4j.Log4j2;
import org.antlr.stringtemplate.StringTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

/** Created by Safeer Ansari on 9/12/2018. */
@Service
@Log4j2
public class SmsSenderImpl implements SmsSender {

  @Autowired private SMSGateway smsGateway;

  @Async
  @Override
  public void sendSMS(SmsDto smsDto) throws SMSException {
    log.info("sms information:" + smsDto);
    StringTemplate messageTemplate = new StringTemplate(smsDto.getNotificationMessage());
    for (Map.Entry pair : smsDto.getData().entrySet()) {
      messageTemplate.setAttribute((String) pair.getKey(), pair.getValue());
    }
    smsGateway.send(smsDto.getRecipient().getRecipientPhone(), messageTemplate.toString());
  }
}
