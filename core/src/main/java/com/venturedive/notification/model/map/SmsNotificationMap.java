package com.venturedive.notification.model.map;

import com.venturedive.notification.model.constant.NotificationConstant;
import com.venturedive.notification.model.constant.SMSNotificationConstant;

import java.util.HashMap;
import java.util.Map;
/**
 * @Param dataMap
 *
 * Sample:
 * {
 *     "order": {
 *         "id": "AZ120023",
 *         "delivery_date" : "24th December, 2019"
 *         "customer": {
 *             "first_name": "Safeer",
 *             "last_name": "Ansari",
 *             "email": "safeer.ansari@venturedive.com"
 *         }
 *     }
 * }
 *
 * @return smsNotificationDataMap
 * Sample:
 * {
 *     "first_name": "Safeer",
 *     "order_"
 * }
 * The parameter `dataMap` has all the data that could be
 * nested in other objects/maps. In order to simplify and extract the data required by all the
 * sms notification templates, each key is searched from the dataMap and put into the returning
 * map called `smsNotificationDataMap`.
 *
 * The keys that are being put in the `smsNotificationDataMap` variable must be same Strings
 * that are being used as a placeholder in the sms notification templates.
 * Example:
 * Key: ORDER_CONFIRMED_CUSTOMER
 * Template: "Hey $customer_first_name$! Your order will delivered on $order_delivery_date$!"
 *
 * data.put("customer_first_name", "Safeer");
 * data.put("order_delivery_date", "24th December, 2019");
 *
 * The final message would look like: "Hey Safeer! Your order will be delivered on 24th December, 2019!"
 *
 * Created by Safeer Ansari on 12/4/2018.
 */
public class SmsNotificationMap extends NotificationMap {
  public static Map<String, Object> getSmsNotificationDataMap(Map<String, Object> dataMap) {
    Map<String, Object> smsNotificationDataMap = new HashMap<>();
    smsNotificationDataMap.put(SMSNotificationConstant.CUSTOMER_FIRST_NAME, DataMap.getValueFromMap(NotificationConstant.FIRST_NAME, dataMap));
    smsNotificationDataMap.put(SMSNotificationConstant.ORDER_DELIVERY_DATE, DataMap.getValueFromMap(NotificationConstant.DELIVERY_DATE, dataMap));
    return smsNotificationDataMap;
  }
}
