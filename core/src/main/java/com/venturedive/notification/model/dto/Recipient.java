package com.venturedive.notification.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.venturedive.notification.model.constant.NFLocale;
import lombok.Data;

import java.util.List;

/** Created by Safeer Ansari on 9/12/2018. */
@Data
public class Recipient {
  @JsonProperty(value = "recipient_name")
  private String recipientName;

  @JsonProperty(value = "recipient_email")
  private String recipientEmail;

  @JsonProperty(value = "recipient_phone")
  private String recipientPhone;

  @JsonProperty(value = "recipient_username")
  private String recipientUsername;

  @JsonProperty(value = "recipient_push_notification_id")
  private List<String> pushNotificationId;

  private NFLocale locale = NFLocale.EN_US;
}
