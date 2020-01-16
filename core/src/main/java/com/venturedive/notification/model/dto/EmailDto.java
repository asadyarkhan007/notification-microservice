package com.venturedive.notification.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/** Created by Safeer Ansari on 9/12/2018. */
@Data
@Builder
public class EmailDto {
  private Recipient recipient;
  private String emailTemplate;
  private String emailSubject;
  private String templateConstant;
  private List<String> attachments;
  private Map<String, Object> data;
  private String cc;
  private String bcc;
}
