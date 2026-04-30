package com.yeoljeong.tripmate.product.application.dto.result;

import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * 날짜 기반 예약 가능 상품 조회 결과 DTO 겸
 * 싱품 에게 넘겨 주는 Product  + Product Schedule 정보 DTO
 * 즉 ProductAvailabilityResult dto
 * (Service → Controller)
 */

public record ProductScheduleInfoResult(
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

  public static ProductScheduleInfoResult from(ProductSchedule schedule) {
    return new ProductScheduleInfoResult(
        schedule.getProduct().getId(),
        schedule.getProduct().getProductName(),
        schedule.getProduct().getAddress().getCountry().name(),
        schedule.getProduct().getAddress().getState(),
        schedule.getProduct().getAddress().getCity(),
        schedule.getProduct().getPrice(),
        schedule.getProduct().getStatus().name(),
        schedule.getId(),
        schedule.getDate(),
        schedule.getStock(),
        schedule.getStatus().name()
    );
  }
}