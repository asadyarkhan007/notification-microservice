package com.venturedive.notification.service.impl;

import com.venturedive.notification.model.constant.NFLocale;
import com.venturedive.notification.model.constant.NotifierType;
import com.venturedive.notification.model.dto.ActionType;
import com.venturedive.notification.model.entity.SMSTemplate;
import com.venturedive.notification.repository.SMSTemplateRepository;
import com.venturedive.notification.repository.SpecificTemplateOnlyRepository;
import com.venturedive.notification.service.SMSTemplateService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/** Created by Safeer Ansari on 9/11/2018. */
@Service
public class SMSTemplateServiceImpl implements SMSTemplateService {

  @Autowired SMSTemplateRepository smsTemplateRepository;

  @Autowired SpecificTemplateOnlyRepository specificTemplateOnlyRepository;

  @Override
  public SMSTemplate findByKey(String key) {
    return smsTemplateRepository.findByKey(key);
  }

  private NotifierType notifierType = NotifierType.SMS;

  @Override
  public SMSTemplate findByActionType(ActionType actionType) {
    SMSTemplate smsTemplate = null;
    List<String> keys =
        new ArrayList<>(Collections.singletonList(actionType.getSpecificActionType()));
    if (specificTemplateOnlyRepository.countByKeyAndNotifier(
            actionType.getKey(), notifierType.getValue())
        <= 0) {
      keys.add(actionType.getGeneralActionType());
    }
    List<SMSTemplate> smsTemplates = smsTemplateRepository.findByKeyIn(keys);
    if (Objects.nonNull(smsTemplates) && !smsTemplates.isEmpty()) {
      if (smsTemplates.size() == NumberUtils.INTEGER_ONE) {
        smsTemplate = smsTemplates.get(NumberUtils.INTEGER_ZERO);
      } else {
        smsTemplate =
            smsTemplates
                .stream()
                .filter(
                    template ->
                        actionType.getSpecificActionType().equalsIgnoreCase(template.getKey()))
                .findFirst()
                .orElse(smsTemplates.get(NumberUtils.INTEGER_ZERO));
      }
    }
    return smsTemplate;
  }

  @Override
  public SMSTemplate findByActionType(ActionType actionType, NFLocale locale) {
    SMSTemplate smsTemplate = null;
    List<String> keys =
        new ArrayList<>(Collections.singletonList(actionType.getSpecificActionType()));
    if (specificTemplateOnlyRepository.countByKeyAndNotifier(
            actionType.getKey(), notifierType.getValue())
        <= 0) {
      keys.add(actionType.getGeneralActionType());
    }
    List<SMSTemplate> i18nSmsTemplates =
        smsTemplateRepository.findByKeyInAndLanguageCode(keys, locale.getLanguageCode());
    if (Objects.nonNull(i18nSmsTemplates) && !i18nSmsTemplates.isEmpty()) {
      List<SMSTemplate> smsTemplates =
          i18nSmsTemplates
              .stream()
              .filter(
                  temp -> locale.getLanguageCode().equalsIgnoreCase(temp.getLanguage().getCode()))
              .collect(Collectors.toList());
      if (smsTemplates.size() == 0) {
        smsTemplates = i18nSmsTemplates;
      }
      if (smsTemplates.size() == NumberUtils.INTEGER_ONE) {
        smsTemplate = smsTemplates.get(NumberUtils.INTEGER_ZERO);
      } else {
        smsTemplate =
            smsTemplates
                .stream()
                .filter(
                    template ->
                        actionType.getSpecificActionType().equalsIgnoreCase(template.getKey()))
                .findFirst()
                .orElse(smsTemplates.get(NumberUtils.INTEGER_ZERO));
      }
    }
    return smsTemplate;
  }
}
