package com.venturedive.notification.service;

import com.venturedive.notification.model.constant.NFLocale;
import com.venturedive.notification.model.dto.ActionType;
import com.venturedive.notification.model.entity.EmailTemplate;

/** Created by Safeer Ansari on 9/11/2018. */
public interface EmailTemplateService {
  EmailTemplate findByKey(String key);

  EmailTemplate findByActionType(ActionType actionType);

  EmailTemplate findByActionType(ActionType actionType, NFLocale locale);

  EmailTemplate findById(Integer id);
}
