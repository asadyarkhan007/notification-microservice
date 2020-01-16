package com.venturedive.notification.model.entity;

import lombok.Data;

import javax.persistence.*;

/** Created by Safeer Ansari on 9/10/2018. */
@Entity
@Table(name = "specific_template_only")
@Data
public class SpecificTemplateOnly {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "key")
  private String key;

  @Column(name = "notifier")
  private String notifier;
}
