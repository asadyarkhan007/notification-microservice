package com.venturedive.notification.model.dto;

import lombok.Data;
import org.springframework.util.ObjectUtils;

/**
 * The ActionType allows you to generate a key for the notification that is needed to be sent.
 * Each ActionType has the following 4 parts, in the below, sequential, order:
 *
 * 1. moduleType        (e.g. BOOKING)
 * 2. notificationEvent (e.g. CREATED)
 * 3. receiverType      (e.g. CUSTOMER)
 * 4. key (optional)    (e.g. BY_ADMIN)
 *
 * The class ActionType as following two methods:
 *
 * 1. getGeneralActionType()
 * 2. getSpecificActionType()
 *
 * Both the methods, when called, would generate following keys
 *
 * 1. getGeneralActionType(): BOOKING_CREATED_CUSTOMER
 * 2. getSpecificActionType(): BOOKING_CREATED_CUSTOMER_BY_ADMIN
 *
 * The purpose of having an extra `key` is to send out a notification with a different template,
 * on the same action.
 *
 * For example, a booking is created by a customer, would send out a notification associated to
 * the ActionType 'BOOKING_CREATED_CUSTOMER', whereas, in case the booking is created by the admin,
 * on behalf of the customer, it will send out the notification with a different template.
 *
 * Note that if a key-specific template is not found, the system would then search for a template
 * with a 'BOOKING_CREATED_CUSTOMER' and send out a notification with a template associated to this
 * general ActionType.
 *
 * Created by Safeer Ansari on 9/14/2018.
 */
@Data
public class ActionType {
  private String moduleType;
  private String notificationEvent;
  private String receiverType;
  private String key;

  public String getGeneralActionType() {
    final String SEPERATOR = "_";
    StringBuilder action = new StringBuilder();
    if (!ObjectUtils.isEmpty(this.moduleType)) {
      action.append(this.moduleType);
    }
    if (!ObjectUtils.isEmpty(this.notificationEvent)) {
      action.append(SEPERATOR);
      action.append(this.notificationEvent);
    }
    if (!ObjectUtils.isEmpty(this.receiverType)) {
      action.append(SEPERATOR);
      action.append(this.receiverType);
    }
    return action.toString();
  }

  public String getSpecificActionType() {
    final String SEPERATOR = "_";
    StringBuilder action = new StringBuilder();
    if (!ObjectUtils.isEmpty(this.moduleType)) {
      action.append(this.moduleType);
    }
    if (!ObjectUtils.isEmpty(this.notificationEvent)) {
      action.append(SEPERATOR);
      action.append(this.notificationEvent);
    }
    if (!ObjectUtils.isEmpty(this.receiverType)) {
      action.append(SEPERATOR);
      action.append(this.receiverType);
    }
    if (!ObjectUtils.isEmpty(this.key)) {
      action.append(SEPERATOR);
      action.append(this.key);
    }
    return action.toString();
  }
}
