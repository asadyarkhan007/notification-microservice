package com.venturedive.notification.gateway;

import com.venturedive.notification.exception.NotificationServiceException;
import com.venturedive.notification.model.constant.DeviceType;
import com.venturedive.notification.model.dto.pushnotification.base.GenericDeviceResponse;
import com.venturedive.notification.model.dto.pushnotification.base.GenericPushRequestObject;
import com.venturedive.notification.model.dto.pushnotification.base.GenericPushResponseObject;

import java.util.Map;

/** Created by Safeer Ansari on 9/17/2018. */
public abstract class GenericPushNotificationGateway<T, U> {

  public GenericPushResponseObject push(GenericPushRequestObject genericPushRequestObject)
      throws NotificationServiceException {
    /*Todo: Needs to do it via AOP*/
    return this.transformToGenericResponse(
        this.providerPush(transformToProviderObject(genericPushRequestObject)));
  }

  protected abstract T transformToProviderObject(GenericPushRequestObject genericPushRequestObject);

  protected abstract U providerPush(T providerPushObject) throws NotificationServiceException;

  protected abstract GenericPushResponseObject transformToGenericResponse(U providerResponse)
      throws NotificationServiceException;

  public abstract GenericDeviceResponse verifyDevice(String deviceUUID)
      throws NotificationServiceException;

  public abstract Map<String, String> registerDeviceByNativeDeviceId(
      String deviceId, DeviceType deviceType) throws NotificationServiceException;
}
