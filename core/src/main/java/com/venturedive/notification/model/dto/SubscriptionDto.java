package com.venturedive.notification.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

/** Created by Safeer Ansari on 9/12/2018. */
@Data
@Builder
@ToString
public class SubscriptionDto {
  private Map<String, String> data;
  private String key;
  private String listIds;
  private String email;
}
