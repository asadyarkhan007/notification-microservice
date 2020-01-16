package com.venturedive.notification.service.impl;

import com.venturedive.notification.model.constant.NFLocale;
import com.venturedive.notification.model.constant.NotifierType;
import com.venturedive.notification.model.dto.ActionType;
import com.venturedive.notification.model.entity.EmailTemplate;
import com.venturedive.notification.repository.EmailTemplateRepository;
import com.venturedive.notification.repository.SpecificTemplateOnlyRepository;
import com.venturedive.notification.service.EmailTemplateService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/** Created by Safeer Ansari on 9/11/2018. */
@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

  @Autowired EmailTemplateRepository emailTemplateRepository;

  @Override
  public EmailTemplate findByKey(String key) {
    return emailTemplateRepository.findByKey(key);
  }

  @Autowired SpecificTemplateOnlyRepository specificTemplateOnlyRepository;

  private NotifierType notifierType = NotifierType.EMAIL;

  @Override
  public EmailTemplate findByActionType(ActionType actionType) {
    EmailTemplate emailTemplate = null;
    List<String> keys =
        new ArrayList<>(Collections.singletonList(actionType.getSpecificActionType()));
    if (specificTemplateOnlyRepository.countByKeyAndNotifier(
            actionType.getKey(), notifierType.getValue())
        <= 0) {
      keys.add(actionType.getGeneralActionType());
    }
    List<EmailTemplate> emailTemplates = emailTemplateRepository.findByKeyIn(keys);
    if (Objects.nonNull(emailTemplates) && !emailTemplates.isEmpty()) {
      if (emailTemplates.size() == NumberUtils.INTEGER_ONE) {
        emailTemplate = emailTemplates.get(NumberUtils.INTEGER_ZERO);
      } else {
        emailTemplate =
            emailTemplates
                .stream()
                .filter(
                    template ->
                        actionType.getSpecificActionType().equalsIgnoreCase(template.getKey()))
                .findFirst()
                .orElse(emailTemplates.get(NumberUtils.INTEGER_ZERO));
      }
    }
    return emailTemplate;
  }

  @Override
  public EmailTemplate findByActionType(ActionType actionType, NFLocale locale) {
    EmailTemplate emailTemplate = null;
    List<String> keys =
        new ArrayList<>(Collections.singletonList(actionType.getSpecificActionType()));
    if (specificTemplateOnlyRepository.countByKeyAndNotifier(
            actionType.getKey(), notifierType.getValue())
        <= 0) {
      keys.add(actionType.getGeneralActionType());
    }
    List<EmailTemplate> i18nEmailTemplates =
        emailTemplateRepository.findByKeyInAndLanguageCode(keys, locale.getLanguageCode());
    if (Objects.nonNull(i18nEmailTemplates) && !i18nEmailTemplates.isEmpty()) {
      List<EmailTemplate> emailTemplates =
          i18nEmailTemplates
              .stream()
              .filter(
                  temp -> locale.getLanguageCode().equalsIgnoreCase(temp.getLanguage().getCode()))
              .collect(Collectors.toList());
      if (emailTemplates.size() == 0) {
        emailTemplates = i18nEmailTemplates;
      }
      if (emailTemplates.size() == NumberUtils.INTEGER_ONE) {
        emailTemplate = emailTemplates.get(NumberUtils.INTEGER_ZERO);
      } else {
        emailTemplate =
            emailTemplates
                .stream()
                .filter(
                    template ->
                        actionType.getSpecificActionType().equalsIgnoreCase(template.getKey()))
                .findFirst()
                .orElse(emailTemplates.get(NumberUtils.INTEGER_ZERO));
      }
    }
    return emailTemplate;
  }

  @Override
  public EmailTemplate findById(Integer id) {
    Optional<EmailTemplate> emailTemplateOptional = emailTemplateRepository.findById(id);
    return emailTemplateOptional.orElse(null);
  }
}
