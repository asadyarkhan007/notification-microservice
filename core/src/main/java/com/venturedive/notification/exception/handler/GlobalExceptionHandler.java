package com.venturedive.notification.exception.handler;

import com.venturedive.notification.exception.NotificationServiceException;
import com.venturedive.notification.model.dto.response.BaseResponseDto;
import com.venturedive.notification.model.dto.response.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;

/** Created by Safeer Ansari on 21/02/2018. */
@ControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
  private static final String CAUGHT_MESSAGE = "caught %s. message:%s";

  @Value(value = "${api.sendEmailException.enable}")
  private String sendEmailOnUnHandledException;

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(NotificationServiceException.class)
  public ResponseEntity<BaseResponseDto> handleNotificationException(
      NotificationServiceException ex, WebRequest request) {
    if (LOGGER.isErrorEnabled()) {
      LOGGER.error(String.format(CAUGHT_MESSAGE, ex.getClass().toString(), ex.getMessage()), ex);
    }
    BaseResponseDto response = new BaseResponseDto();
    response.setErrors(Arrays.asList(new ErrorDto(ex.getCode(), ex.getMessage())));
    response.setStatus(HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(response, response.getStatus());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ResponseEntity<BaseResponseDto> handleBaseException(
      Exception ex, ServletWebRequest request) {
    String requestDetail = getRequestDetails();
    LOGGER.debug(requestDetail);
    if (LOGGER.isErrorEnabled()) {
      LOGGER.error(String.format(CAUGHT_MESSAGE, ex.getClass().toString(), ex.getMessage()), ex);
    }
    sendEmail(ex, requestDetail);
    BaseResponseDto response = new BaseResponseDto();
    response.setErrors(
        Arrays.asList(
            new ErrorDto(
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Sorry, Something went wrong while processing your request")));
    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    return new ResponseEntity<>(response, response.getStatus());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<BaseResponseDto> handleBaseException(
      HttpMessageNotReadableException ex, ServletWebRequest request) {
    String requestDetail = getRequestDetails();
    LOGGER.debug(requestDetail);
    if (LOGGER.isErrorEnabled()) {
      LOGGER.error(String.format(CAUGHT_MESSAGE, ex.getClass().toString(), ex.getMessage()), ex);
    }
    sendEmail(ex, requestDetail);
    BaseResponseDto response = new BaseResponseDto();
    response.setErrors(
        Arrays.asList(
            new ErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), "Request Body is missing")));
    response.setStatus(HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(response, response.getStatus());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(UnsupportedOperationException.class)
  public ResponseEntity<BaseResponseDto> handleUnsupportedOperationException(
      UnsupportedOperationException ex) {
    if (LOGGER.isErrorEnabled()) {
      LOGGER.error(String.format(CAUGHT_MESSAGE, ex.getClass().toString(), ex.getMessage()), ex);
    }
    BaseResponseDto response = new BaseResponseDto();
    response.setErrors(
        Arrays.asList(new ErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage())));
    response.setStatus(HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(response, response.getStatus());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<BaseResponseDto> handleIllegalArgumentException(
      IllegalArgumentException ex) {
    if (LOGGER.isErrorEnabled()) {
      LOGGER.error(String.format(CAUGHT_MESSAGE, ex.getClass().toString(), ex.getMessage()), ex);
    }
    BaseResponseDto response = new BaseResponseDto();
    response.setErrors(
        Arrays.asList(new ErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage())));
    response.setStatus(HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(response, response.getStatus());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<BaseResponseDto> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException ex) {
    if (LOGGER.isErrorEnabled()) {
      LOGGER.error(String.format(CAUGHT_MESSAGE, ex.getClass().toString(), ex.getMessage()), ex);
    }
    BaseResponseDto response = new BaseResponseDto();
    response.setErrors(
        Arrays.asList(new ErrorDto(ex.getErrorCode(), ex.getMostSpecificCause().getMessage())));
    response.setStatus(HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(response, response.getStatus());
  }

  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<BaseResponseDto> handleHttpMediaTypeNotSupportedException(
      HttpMediaTypeNotSupportedException ex) {
    if (LOGGER.isErrorEnabled()) {
      LOGGER.error(String.format(CAUGHT_MESSAGE, ex.getClass().toString(), ex.getMessage()), ex);
    }
    BaseResponseDto response = new BaseResponseDto();
    response.setErrors(
        Arrays.asList(
            new ErrorDto(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase(), ex.getMessage())));
    response.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    return new ResponseEntity<>(response, response.getStatus());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<BaseResponseDto> handleMissingServletRequestParameterException(
      MissingServletRequestParameterException ex) {
    if (LOGGER.isErrorEnabled()) {
      LOGGER.error(String.format(CAUGHT_MESSAGE, ex.getClass().toString(), ex.getMessage()), ex);
    }
    BaseResponseDto response = new BaseResponseDto();
    response.setErrors(
        Arrays.asList(new ErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage())));
    response.setStatus(HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(response, response.getStatus());
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(HttpClientErrorException.class)
  public ResponseEntity<BaseResponseDto> handleUnauthorizedException(HttpClientErrorException ex) {
    if (LOGGER.isErrorEnabled()) {
      LOGGER.error(String.format(CAUGHT_MESSAGE, ex.getClass().toString(), ex.getMessage()), ex);
    }
    BaseResponseDto response = new BaseResponseDto();
    response.setErrors(
        Arrays.asList(new ErrorDto(HttpStatus.UNAUTHORIZED.getReasonPhrase(), ex.getMessage())));
    response.setStatus(HttpStatus.UNAUTHORIZED);
    return new ResponseEntity<>(response, response.getStatus());
  }

  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<BaseResponseDto> handleMethodNotSupportedException(
      HttpRequestMethodNotSupportedException ex) {
    if (LOGGER.isErrorEnabled()) {
      LOGGER.error(String.format(CAUGHT_MESSAGE, ex.getClass().toString(), ex.getMessage()), ex);
    }
    BaseResponseDto response = new BaseResponseDto();
    response.setErrors(
        Arrays.asList(
            new ErrorDto(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), ex.getMessage())));
    response.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
    return new ResponseEntity<>(response, response.getStatus());
  }

  private void sendEmail(Exception ex, String requestDetail) {
    if (Boolean.valueOf(sendEmailOnUnHandledException)) {
      LOGGER.debug("Sending exception report via email");
      if (LOGGER.isErrorEnabled()) {
        LoggerFactory.getLogger("emailLogger")
            .error(
                String.format(
                    "caught %s. Request Detail:%s", ex.getClass().toString(), requestDetail),
                ex);
      }
    }
  }

  private String getRequestDetails() {
    StringBuilder sb = new StringBuilder();
    return sb.toString();
  }
}
