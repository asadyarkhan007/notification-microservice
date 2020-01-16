package com.venturedive.notification.model.map;

import org.antlr.stringtemplate.StringTemplate;

import java.util.Map;
import java.util.Objects;

/** Created by Safeer Ansari on 11/13/2018. */
public class DataMap {
  public static String getValueFromMap(String key, Map<String, Object> data) {
    for (Map.Entry<String, Object> entry : data.entrySet()) {
      if (entry.getKey().equals(key) && !(entry.getValue() instanceof Map)) {
        return Objects.nonNull(entry.getValue()) ? entry.getValue().toString() : null;
      }
      if (entry.getValue() instanceof Map) {
        String result = getValueFromMap(key, (Map<String, Object>) entry.getValue());
        if (Objects.nonNull(result)) {
          return result;
        }
      }
    }
    return null;
  }

  public static StringTemplate populateStringTemplate(
      Map<String, Object> data, StringTemplate stringTemplate) {
    for (Map.Entry<String, Object> entry : data.entrySet()) {
      stringTemplate.setAttribute(entry.getKey(), entry.getValue());
      if (entry.getValue() instanceof Map) {
        stringTemplate =
            populateStringTemplate((Map<String, Object>) entry.getValue(), stringTemplate);
      }
    }
    return stringTemplate;
  }
}
