package com.venturedive.notification.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/** Created by Safeer Ansari on 11/13/2018. */
@Configuration
public class SpringApplicationContext implements ApplicationContextAware {

  private static ApplicationContext CONTEXT;

  public static Object getBean(String name) {
    return CONTEXT.getBean(name);
  }

  public void setApplicationContext(ApplicationContext ctx) throws BeansException {
    CONTEXT = ctx;
  }
}
