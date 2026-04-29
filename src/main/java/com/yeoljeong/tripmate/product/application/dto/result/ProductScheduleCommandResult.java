package com.yeoljeong.tripmate.product.application.dto.result;

import java.time.LocalDate;
import java.util.UUID;

/**
 * 상품 스케줄 생성 결과 DTO
 * (Service → Controller)
 */
public record ProductScheduleCommandResult(
    UUID productId,
    int createdCount,
    LocalDate startDate,
    LocalDate endDate
) {
  public static ProductScheduleCommandResult of(
      UUID productId,
      int count,
      LocalDate startDate,
      LocalDate endDate
  ) {
    return new ProductScheduleCommandResult(productId, count, startDate, endDate);
  }
}