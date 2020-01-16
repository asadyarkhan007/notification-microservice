package com.venturedive.notification.model.entity.translation;

import com.venturedive.notification.model.entity.Language;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class Translation {

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "language_id")
  private Language language;
}
