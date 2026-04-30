package com.yeoljeong.tripmate.product.presentation.dto.response;

import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleInfoResult;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ProductScheduleInfoResponse(
    // Product 정보
    UUID productId,
    String productName,
    String country,
    String state,
    String city,
    BigDecimal price,
    String productStatus,

    //Product Schedule 정보
    UUID scheduleId,
    LocalDate date,
    int stock,
    String scheduleStatus
) {
  public static ProductScheduleInfoResponse from(ProductScheduleInfoResult result) {
    return new ProductScheduleInfoResponse(
        result.productId(),
        result.productName(),
        result.country(),
        result.state(),
        result.city(),
        result.price(),
        result.productStatus(),
        result.scheduleId(),
        result.date(),
        result.stock(),
        result.scheduleStatus()
    );
  }
}
