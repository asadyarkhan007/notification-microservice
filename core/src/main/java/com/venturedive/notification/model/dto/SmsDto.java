package com.venturedive.notification.model.dto;

/** Created by Safeer Ansari on 9/12/2018. */

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class SmsDto {
  private Recipient recipient;
  private String templateKey;
  private String notificationMessage;
  private Map<String, Object> data = new HashMap<>();
}
