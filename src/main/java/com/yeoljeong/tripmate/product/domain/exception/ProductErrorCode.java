package com.yeoljeong.tripmate.product.domain.exception;

import com.yeoljeong.tripmate.exception.constants.ErrorCode;
import org.springframework.http.HttpStatus;

public enum ProductErrorCode implements ErrorCode {

  // 상품
  PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다."),
  INVALID_PRODUCT(HttpStatus.BAD_REQUEST, "상품은 필수입니다."),
  INVALID_PRODUCT_PRICE(HttpStatus.BAD_REQUEST, "상품 가격이 올바르지 않습니다."),
  INVALID_PRODUCT_ADDRESS(HttpStatus.BAD_REQUEST, "상품 주소가 올바르지 않습니다."),

  // 스케줄 - 날짜
  INVALID_DATE(HttpStatus.BAD_REQUEST, "날짜는 필수입니다."),
  INVALID_DATE_RANGE(HttpStatus.BAD_REQUEST, "스케줄 생성 가능한 날짜 범위는 최대 31일입니다."),
  SCHEDULE_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 해당 날짜의 스케줄이 존재합니다."),

  // 스케줄 - 재고
  INVALID_STOCK(HttpStatus.BAD_REQUEST, "재고는 1 이상이어야 합니다."),
  INVALID_QUANTITY(HttpStatus.BAD_REQUEST, "차감 수량은 1 이상이어야 합니다."),
  INSUFFICIENT_STOCK(HttpStatus.CONFLICT, "재고가 부족합니다."),

  // 스케줄 - 상태
  ALREADY_CLOSED(HttpStatus.CONFLICT, "이미 종료된 일정입니다."),

  // 스케줄 - 조회
  SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "스케줄을 찾을 수 없습니다.");


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
