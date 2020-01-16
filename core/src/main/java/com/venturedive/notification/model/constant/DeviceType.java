package com.venturedive.notification.model.constant;

/** Created by Safeer Ansari on 9/17/2018. */
public enum DeviceType {
  ANDROID("android"),
  IOS("iOS"),
  WEBSITE("website"),
  OTHER("other");

  private String code;

  private DeviceType(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
