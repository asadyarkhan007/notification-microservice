package com.venturedive.notification.service;

import com.venturedive.notification.exception.SMSException;
import com.venturedive.notification.model.dto.SmsDto;

/** Created by Safeer Ansari on 9/12/2018. */
public interface SmsSender {

  public void sendSMS(SmsDto smsDto) throws SMSException;
}
