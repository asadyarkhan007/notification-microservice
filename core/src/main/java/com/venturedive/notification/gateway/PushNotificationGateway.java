package com.venturedive.notification.gateway;

import com.venturedive.notification.exception.PushNotificationException;

import java.util.Map;

public interface PushNotificationGateway<T> {
  T send(Map<String, Object> pushNotificationMap) throws PushNotificationException;
}
