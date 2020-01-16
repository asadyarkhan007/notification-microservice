package com.venturedive.notification.model.dto.pushnotification.base;

import com.venturedive.notification.model.constant.DeviceType;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Created by Safeer Ansari on 26/07/2017. */
@Data
@NoArgsConstructor
public class GenericDeviceResponse {

  private Boolean status;
  private String statusCode;
  private String responseDetail;
  private DeviceType deviceType;
}
