package com.venturedive.notification.model.constant;

/** Created by Safeer Ansari on 9/18/2018. */
public enum ReceiverType {
  CUSTOMER("CUSTOMER"),
  PARTNER("PARTNER");

  private String value;

  ReceiverType(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
