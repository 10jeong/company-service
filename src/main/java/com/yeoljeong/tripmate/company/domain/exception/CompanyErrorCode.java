package com.yeoljeong.tripmate.company.domain.exception;

import com.yeoljeong.tripmate.exception.constants.ErrorCode;
import org.springframework.http.HttpStatus;

public enum CompanyErrorCode implements ErrorCode {

  COMPANY_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 등록된 사업자입니다."),
  COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "회사를 찾을 수 없습니다.");

  private final HttpStatus status;
  private final String message;

  CompanyErrorCode(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

  @Override
  public int getCode() {
    return status.value();
  }

  @Override
  public HttpStatus getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return message;
  }
}