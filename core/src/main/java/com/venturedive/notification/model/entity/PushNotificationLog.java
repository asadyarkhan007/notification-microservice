package com.venturedive.notification.model.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/** Created by Safeer Ansari on 9/24/2018. */
@Data
@MappedSuperclass
public class PushNotificationLog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String notificationType;
  private String notificationStatus;
  private String notificationMessage;
  private LocalDateTime creationTime;
  private Boolean readStatus;
  private String responseToken;
  private String requestPayload;
  private String responsePayload;
}
