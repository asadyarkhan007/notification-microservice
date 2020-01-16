package com.venturedive.notification.exception;

/** Created by Safeer Ansari on 9/10/2018. */
public class EmailException extends NotificationServiceException {

  public EmailException(String code, String ms, Exception e) {
    super(code, ms, e);
  }
}
