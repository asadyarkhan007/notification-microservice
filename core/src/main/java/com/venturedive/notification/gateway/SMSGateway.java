package com.venturedive.notification.gateway;

import com.venturedive.notification.exception.SMSException;

public interface SMSGateway<T> {
  T send(String phoneNumber, String message) throws SMSException;
}
