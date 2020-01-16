package com.venturedive.notification.util;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/** Created by Safeer Ansari on 11/13/2018. */
@Component
public class ObjectUtility {
  public static <T> List<T> safeList(List<T> list) {
    return list == null ? Collections.<T>emptyList() : list;
  }
}
