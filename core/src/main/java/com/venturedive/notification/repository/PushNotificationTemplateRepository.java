package com.venturedive.notification.repository;

import com.venturedive.notification.model.entity.PushNotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** Created by Safeer Ansari on 9/17/2018. */
public interface PushNotificationTemplateRepository
    extends JpaRepository<PushNotificationTemplate, Integer> {
  PushNotificationTemplate findByKey(String key);

  List<PushNotificationTemplate> findByKeyIn(List<String> keys);

  List<PushNotificationTemplate> findByKeyInAndLanguageCode(List<String> keys, String languageCode);
}
