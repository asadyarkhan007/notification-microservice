package com.venturedive.notification.model.entity;

import com.venturedive.notification.model.entity.translation.Translation;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** Created by Safeer Ansari on 9/17/2018. */
@Entity
@Table(name = "push_notification_template")
@Data
public class PushNotificationTemplate extends Translation {

  @Id private Integer id;

  @Column(name = "key")
  private String key;

  @Column(name = "title")
  private String title;

  @Column(name = "text")
  private String text;

  @Column(name = "is_active")
  private Boolean isActive;
}
