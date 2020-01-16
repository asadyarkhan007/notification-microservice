package com.venturedive.notification.repository;

import com.venturedive.notification.model.entity.SMSTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Created by Safeer Ansari on 9/11/2018. */
@Repository
public interface SMSTemplateRepository extends CrudRepository<SMSTemplate, Integer> {

  SMSTemplate findByKey(String key);

  List<SMSTemplate> findByKeyIn(List<String> keys);

  List<SMSTemplate> findByKeyInAndLanguageCode(List<String> keys, String languageCode);
}
