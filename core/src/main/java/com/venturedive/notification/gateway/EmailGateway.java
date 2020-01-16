package com.venturedive.notification.gateway;

import com.venturedive.notification.exception.EmailException;

public interface EmailGateway<T> {
  T send(String subject, String content, String to) throws EmailException;

  T send(String subject, String content, String to, String cc, String bcc) throws EmailException;
}
