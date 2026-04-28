package com.yeoljeong.tripmate.product.domain.exception;

import com.yeoljeong.tripmate.exception.constants.ErrorCode;
import org.springframework.http.HttpStatus;

public enum ProductErrorCode implements ErrorCode {

  PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다."),
  INVALID_PRODUCT_PRICE(HttpStatus.BAD_REQUEST, "상품 가격이 올바르지 않습니다."),
  INVALID_PRODUCT(HttpStatus.BAD_REQUEST, "상품은 필수입니다."),
  INVALID_PRODUCT_ADDRESS(HttpStatus.BAD_REQUEST, "상품 주소가 올바르지 않습니다."),
  INVALID_DATE(HttpStatus.BAD_REQUEST, "날짜는 필수입니다."),
  INVALID_STOCK(HttpStatus.BAD_REQUEST, "재고는 1 이상이어야 합니다."),
  INVALID_QUANTITY(HttpStatus.BAD_REQUEST, "차감 수량은 1 이상이어야 합니다."),
  INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST, "재고가 부족합니다."),
  ALREADY_CLOSED(HttpStatus.BAD_REQUEST, "이미 종료된 일정입니다.");

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
