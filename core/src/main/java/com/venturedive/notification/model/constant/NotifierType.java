package com.venturedive.notification.model.constant;

/** Created by Safeer Ansari on 9/18/2018. */
public enum NotifierType {
  EMAIL("EMAIL"),
  SMS("SMS"),
  PN("PN");

  private String value;

  NotifierType(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
