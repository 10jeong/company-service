package com.yeoljeong.tripmate.product.presentation.dto.request;

import com.yeoljeong.tripmate.product.application.dto.command.CreateProductScheduleCommand;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductScheduleRequest {

  @NotNull(message = "시작일은 필수입니다.")
  private LocalDate startDate;
  @NotNull(message = "종료일은 필수입니다.")
  private LocalDate endDate;
  @Min(value = 1, message = "재고는 1 이상이어야 합니다.")
  private int stock;

  //Request → Command 변환
  public CreateProductScheduleCommand toCommand(UUID productId) {
    return CreateProductScheduleCommand.builder()
        .productId(productId)
        .startDate(startDate)
        .endDate(endDate)
        .stock(stock)
        .build();
  }
}