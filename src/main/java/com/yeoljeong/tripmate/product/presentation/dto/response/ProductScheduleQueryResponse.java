package com.yeoljeong.tripmate.product.presentation.dto.response;

import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleQueryResult;
import java.time.LocalDate;
import java.util.UUID;

public record ProductScheduleQueryResponse(
    UUID scheduleId,
    UUID productId,
    LocalDate date,
    int stock,
    String status
) {

  public static ProductScheduleQueryResponse from(ProductScheduleQueryResult result) {
    return new ProductScheduleQueryResponse(
        result.scheduleId(),
        result.productId(),
        result.date(),
        result.stock(),
        result.status()
    );
  }
}
