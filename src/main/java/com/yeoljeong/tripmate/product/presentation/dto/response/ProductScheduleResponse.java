package com.yeoljeong.tripmate.product.presentation.dto.response;

import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleCommandResult;
import java.time.LocalDate;
import java.util.UUID;

// 상품 스케줄 일괄 생성 응답
public record ProductScheduleResponse(
    UUID productId,
    int createdCount,
    LocalDate startDate,
    LocalDate endDate
) {

  // Result → Response 변환
  public static ProductScheduleResponse from(ProductScheduleCommandResult result) {
    return new ProductScheduleResponse(
        result.productId(),
        result.createdCount(),
        result.startDate(),
        result.endDate()
    );
  }
}