package com.yeoljeong.tripmate.product.domain.exception;

import com.yeoljeong.tripmate.exception.constants.ErrorCode;
import org.springframework.http.HttpStatus;

public enum ProductErrorCode implements ErrorCode {

  PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다."),
  INVALID_PRODUCT_PRICE(HttpStatus.BAD_REQUEST, "상품 가격이 올바르지 않습니다.");

  private final HttpStatus status;
  private final String message;

  ProductErrorCode(HttpStatus status, String message) {
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
