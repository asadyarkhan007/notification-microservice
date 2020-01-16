package com.venturedive.notification.model.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Created by Safeer Ansari on 29/10/2018. */
@Data
public class BaseResponseDto<T> implements Serializable {

  private static final long serialVersionUID = 112345534234234L;
  private HttpStatus status = HttpStatus.OK;
  private T data = null;
  private List<ErrorDto> errors = new ArrayList<>();

  public void makeSuccessResponse() {
    this.setStatus(HttpStatus.OK);
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public void makeSuccessResponse(T data) {
    this.makeSuccessResponse(HttpStatus.OK, data);
  }

  public void makeSuccessResponse(HttpStatus status, T data) {
    this.status = status;
    this.data = data;
  }

  public void makeFailureResponse(T data) {
    this.makeFailureResponse(HttpStatus.OK, data);
  }

  public void makeFailureResponse() {
    this.setStatus(HttpStatus.BAD_REQUEST);
  }

  public void makeFailureResponse(HttpStatus status, T data) {
    this.status = status;
    this.data = data;
  }
}
