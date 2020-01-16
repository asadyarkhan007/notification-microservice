package com.venturedive.notification.model.dto.pushnotification.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Map;

/** Created by Safeer Ansari on 24/07/2017. */
@NoArgsConstructor
@Data
public class GenericPushRequestObject {
  private ArrayList<String> deviceUUIDs;
  private String title;
  private String subtitle;
  private String message;
  private String languageCode;
  private String clickURL;
  private String imageUrl;
  private Map<String, Object> optionalData;
  private String sound;
  private String channelId;

  public GenericPushRequestObject(
      ArrayList<String> deviceUUIDs,
      String title,
      String message,
      String languageCode,
      Map<String, Object> optionalData,
      String channelId) {
    this.deviceUUIDs = deviceUUIDs;
    this.title = title;
    this.message = message;
    this.languageCode = languageCode;
    this.optionalData = optionalData;
    this.setSound("default");
    this.channelId = channelId;
  }
}
