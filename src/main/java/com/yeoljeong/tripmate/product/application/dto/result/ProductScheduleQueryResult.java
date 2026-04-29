package com.yeoljeong.tripmate.product.application.dto.result;

import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import java.time.LocalDate;
import java.util.UUID;

/**
 * 상품 스케줄 조회(단건조회,목록조회) 결과 DTO
 * (Service → Controller)
 */
public record ProductScheduleQueryResult(
    UUID scheduleId,
    UUID productId,
    LocalDate date,
    int stock,
    String status
) {
  public static ProductScheduleQueryResult from(ProductSchedule schedule) {
    return new ProductScheduleQueryResult(
        schedule.getId(),
        schedule.getProduct().getId(),
        schedule.getDate(),
        schedule.getStock(),
        schedule.getStatus().name()
    );
  }
}