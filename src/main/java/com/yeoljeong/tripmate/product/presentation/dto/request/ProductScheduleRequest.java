package com.yeoljeong.tripmate.product.presentation.dto.request;

import com.yeoljeong.tripmate.product.application.dto.command.CreateProductScheduleCommand;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductScheduleRequest {

  private UUID productId;
  private LocalDate startDate;
  private LocalDate endDate;
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