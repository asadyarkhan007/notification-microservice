package com.venturedive.notification.repository;

import com.venturedive.notification.model.entity.EmailTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Created by Safeer Ansari on 9/11/2018. */
@Repository
public interface EmailTemplateRepository extends CrudRepository<EmailTemplate, Integer> {

  EmailTemplate findByKey(String key);

  List<EmailTemplate> findByKeyIn(List<String> keys);

  List<EmailTemplate> findByKeyInAndLanguageCode(List<String> keys, String languageCode);
}
