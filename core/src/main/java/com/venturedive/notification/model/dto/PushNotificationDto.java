package com.venturedive.notification.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

/** Created by Safeer Ansari on 9/12/2018. */
@Data
@Builder
@ToString
public class PushNotificationDto {
  private Recipient recipient;
  private String notificationTitle;
  private String notificationMessage;
  private String receiverType;
  private String notificationEvent;
  private Map<String, Object> data;
}
