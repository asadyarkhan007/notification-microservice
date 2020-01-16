package com.venturedive.notification.model.map;

import com.venturedive.notification.model.constant.NotificationConstant;
import com.venturedive.notification.model.constant.PushNotificationConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * @Param dataMap
 *
 * Sample:
 * {
 *     "order": {
 *         "id": "AZ120023",
 *         "customer": {
 *             "first_name": "Safeer",
 *             "last_name": "Ansari",
 *             "email": "safeer.ansari@venturedive.com"
 *         }
 *     }
 * }
 *
 * @return pushNotificationDataMap
 * Sample:
 * {
 *     "first_name": "Safeer"
 * }
 * The parameter `dataMap` has all the data that could be
 * nested in other objects/maps. In order to simplify and extract the data required by all the
 * push notification templates, each key is searched from the dataMap and put into the returning
 * map called `pushNotificationDataMap`.
 *
 * The keys that are being put in the `pushNotificationDataMap` variable must be same Strings
 * that are being used as a placeholder in the push notification templates.
 * Example:
 * Key: ORDER_CONFIRMED_CUSTOMER
 * Template: "Hey $customer_first_name$! Your order has been confirmed!"
 *
 * data.put("customer_first_name", "Safeer");
 * The final message would look like: "Hey Safeer! Your order number been confirmed!"
 *
 * Created by Safeer Ansari on 12/4/2018.
 */
public class PushNotificationMap extends NotificationMap {
  public static Map<String, Object> getPushNotificationDataMap(Map<String, Object> dataMap) {
    Map<String, Object> pushNotificationDataMap = new HashMap<>();
    pushNotificationDataMap.put(PushNotificationConstant.CUSTOMER_FIRST_NAME, DataMap.getValueFromMap(NotificationConstant.FIRST_NAME, dataMap));
    return pushNotificationDataMap;
  }
}
