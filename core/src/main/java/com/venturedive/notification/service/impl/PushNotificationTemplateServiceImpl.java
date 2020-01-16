package com.venturedive.notification.service.impl;

import com.venturedive.notification.model.constant.NFLocale;
import com.venturedive.notification.model.constant.NotifierType;
import com.venturedive.notification.model.dto.ActionType;
import com.venturedive.notification.model.entity.PushNotificationTemplate;
import com.venturedive.notification.repository.PushNotificationTemplateRepository;
import com.venturedive.notification.repository.SpecificTemplateOnlyRepository;
import com.venturedive.notification.service.PushNotificationTemplateService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/** Created by Safeer Ansari on 01/30/2019. */
@Service
public class PushNotificationTemplateServiceImpl implements PushNotificationTemplateService {

  @Autowired PushNotificationTemplateRepository pushNotificationTemplateRepository;

  @Override
  public PushNotificationTemplate findByKey(String key) {
    return pushNotificationTemplateRepository.findByKey(key);
  }

  @Autowired SpecificTemplateOnlyRepository specificTemplateOnlyRepository;

  private NotifierType notifierType = NotifierType.PN;

  @Override
  public PushNotificationTemplate findByActionType(ActionType actionType) {
    PushNotificationTemplate pushNotificationTemplate = null;
    List<String> keys =
        new ArrayList<>(Collections.singletonList(actionType.getSpecificActionType()));
    if (specificTemplateOnlyRepository.countByKeyAndNotifier(
            actionType.getKey(), notifierType.getValue())
        <= 0) {
      keys.add(actionType.getGeneralActionType());
    }
    List<PushNotificationTemplate> pushNotificationTemplates =
        pushNotificationTemplateRepository.findByKeyIn(keys);
    if (Objects.nonNull(pushNotificationTemplates) && !pushNotificationTemplates.isEmpty()) {
      if (pushNotificationTemplates.size() == NumberUtils.INTEGER_ONE) {
        pushNotificationTemplate = pushNotificationTemplates.get(NumberUtils.INTEGER_ZERO);
      } else {
        pushNotificationTemplate =
            pushNotificationTemplates
                .stream()
                .filter(
                    template ->
                        actionType.getSpecificActionType().equalsIgnoreCase(template.getKey()))
                .findFirst()
                .orElse(pushNotificationTemplates.get(NumberUtils.INTEGER_ZERO));
      }
    }
    return pushNotificationTemplate;
  }

  @Override
  public PushNotificationTemplate findByActionType(ActionType actionType, NFLocale locale) {
    PushNotificationTemplate pushNotificationTemplate = null;
    List<String> keys =
        new ArrayList<>(Collections.singletonList(actionType.getSpecificActionType()));
    if (specificTemplateOnlyRepository.countByKeyAndNotifier(
            actionType.getKey(), notifierType.getValue())
        <= 0) {
      keys.add(actionType.getGeneralActionType());
    }
    List<PushNotificationTemplate> i18nPushNotificationTemplates =
        pushNotificationTemplateRepository.findByKeyInAndLanguageCode(
            keys, locale.getLanguageCode());
    if (Objects.nonNull(i18nPushNotificationTemplates)
        && !i18nPushNotificationTemplates.isEmpty()) {
      List<PushNotificationTemplate> pushNotificationTemplates =
          i18nPushNotificationTemplates
              .stream()
              .filter(
                  temp -> locale.getLanguageCode().equalsIgnoreCase(temp.getLanguage().getCode()))
              .collect(Collectors.toList());
      if (pushNotificationTemplates.size() == 0) {
        pushNotificationTemplates = i18nPushNotificationTemplates;
      }
      if (pushNotificationTemplates.size() == NumberUtils.INTEGER_ONE) {
        pushNotificationTemplate = pushNotificationTemplates.get(NumberUtils.INTEGER_ZERO);
      } else {
        pushNotificationTemplate =
            pushNotificationTemplates
                .stream()
                .filter(
                    template ->
                        actionType.getSpecificActionType().equalsIgnoreCase(template.getKey()))
                .findFirst()
                .orElse(pushNotificationTemplates.get(NumberUtils.INTEGER_ZERO));
      }
    }
    return pushNotificationTemplate;
  }
}
