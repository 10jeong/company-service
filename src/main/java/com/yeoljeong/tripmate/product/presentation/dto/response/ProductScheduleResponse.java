package com.yeoljeong.tripmate.product.presentation.dto.response;

import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleCommandResult;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;

@Getter
public class ProductScheduleResponse {

  private final UUID productId;
  private final int createdCount;
  private final LocalDate startDate;
  private final LocalDate endDate;

  private ProductScheduleResponse(
      UUID productId,
      int createdCount,
      LocalDate startDate,
      LocalDate endDate
  ) {
    this.productId = productId;
    this.createdCount = createdCount;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  //Result → Response 변환
  public static ProductScheduleResponse from(ProductScheduleCommandResult result) {
    return new ProductScheduleResponse(
        result.productId(),
        result.createdCount(),
        result.startDate(),
        result.endDate()
    );
  }
}
