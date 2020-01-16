package com.venturedive.notification.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/** Created by Venturedive on 21/02/2018. */
@Data
@AllArgsConstructor
public class ErrorDto {
  private String code;
  private String message;
}
