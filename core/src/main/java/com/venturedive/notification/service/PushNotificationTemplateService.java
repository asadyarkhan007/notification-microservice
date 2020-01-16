package com.venturedive.notification.service;

import com.venturedive.notification.model.constant.NFLocale;
import com.venturedive.notification.model.dto.ActionType;
import com.venturedive.notification.model.entity.PushNotificationTemplate;

/** Created by Safeer Ansari on 01/30/2019. */
public interface PushNotificationTemplateService {
  PushNotificationTemplate findByKey(String key);

  PushNotificationTemplate findByActionType(ActionType actionType);

  PushNotificationTemplate findByActionType(ActionType actionType, NFLocale locale);
}
