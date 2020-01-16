package com.venturedive.notification.model.dto.pushnotification.base;

import lombok.Data;
import lombok.NoArgsConstructor;

/** Created by Safeer Ansari on 25/07/2017. */
@Data
@NoArgsConstructor
public class GenericPushResponseObject {
  Boolean status;
  String statusCode;
  String responseDetail;
  String responseId;
  Integer receivers;
}
