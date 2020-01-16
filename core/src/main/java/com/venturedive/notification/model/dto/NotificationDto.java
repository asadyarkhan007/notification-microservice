package com.venturedive.notification.model.dto;

import com.venturedive.notification.model.constant.NFLocale;
import lombok.*;

import java.util.List;
import java.util.Map;

/** Created by Safeer Ansari on 9/12/2018. */
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationDto {

  private ActionType actionType;
  private List<Recipient> recipients;
  private List<String> attachments;
  private Map<String, Object> data;
  private Map<NFLocale, Object> languageData;
}
