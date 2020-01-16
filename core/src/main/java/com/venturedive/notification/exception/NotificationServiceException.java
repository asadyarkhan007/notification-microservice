package com.venturedive.notification.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/** Created by Safeer Ansari on 9/10/2018. */
@Getter
@EqualsAndHashCode(callSuper = true)
public class NotificationServiceException extends Exception {

  private static final long serialVersionUID = 1L;

  private final String code;
  private final String message;

  public NotificationServiceException() {
    super();
    this.code = null;
    this.message = null;
  }

  public NotificationServiceException(String code, String ms, Exception e) {
    super(ms, e);
    this.code = code;
    this.message = ms;
  }

  public NotificationServiceException(String code, String ms) {
    super(ms);
    this.code = code;
    this.message = ms;
  }

  public <K> NotificationServiceException(String code, String ms, K object, Exception e) {
    super(ms, e);
    this.code = code;

    if (object instanceof String) {
      this.message = ms + "[" + object + "]";
    } else if (object instanceof Integer) {
      this.message = ms + "[ID #" + object + "]";
    } else {
      this.message = ms + "[" + object.toString() + "]";
    }
  }

  public <K> NotificationServiceException(String code, String ms, K object) {
    super(ms);
    this.code = code;
    this.message = ms + "[" + object.toString() + "]";
  }
}
