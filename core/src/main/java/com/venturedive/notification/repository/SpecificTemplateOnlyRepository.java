package com.venturedive.notification.repository;

import com.venturedive.notification.model.entity.SpecificTemplateOnly;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** Asad Khan */
@Repository
public interface SpecificTemplateOnlyRepository
    extends CrudRepository<SpecificTemplateOnly, Integer> {

  Integer countByKeyAndNotifier(String key, String notifier);
}
