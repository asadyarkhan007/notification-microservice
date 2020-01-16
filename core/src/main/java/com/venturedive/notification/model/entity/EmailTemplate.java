package com.venturedive.notification.model.entity;

import com.venturedive.notification.model.entity.translation.Translation;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** Created by Safeer Ansari on 9/10/2018. */
@Entity
@Table(name = "email_template")
@Data
public class EmailTemplate extends Translation {

  @Id private Integer id;

  @Column(name = "key")
  private String key;

  @Column(name = "content")
  private String content;

  @Column(name = "cc")
  private String cc;

  @Column(name = "bcc")
  private String bcc;
}
