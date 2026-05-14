package com.yeoljeong.tripmate.product.presentation.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleInfoResult;

public record ProductScheduleSimpleResponse(
    UUID productId,
    UUID scheduleId,
    String productName,
    BigDecimal price
) {
  public static ProductScheduleSimpleResponse from(ProductScheduleInfoResult result) {
    return new ProductScheduleSimpleResponse(
        result.productId(),
        result.scheduleId(),
        result.productName(),
        result.price()
    );
  }
}