package com.venturedive.notification.service;

import com.venturedive.notification.model.constant.NFLocale;
import com.venturedive.notification.model.dto.ActionType;
import com.venturedive.notification.model.entity.SMSTemplate;

/** Created by Safeer Ansari on 9/11/2018. */
public interface SMSTemplateService {
  SMSTemplate findByKey(String key);

  SMSTemplate findByActionType(ActionType actionType);

  SMSTemplate findByActionType(ActionType actionType, NFLocale locale);
}
