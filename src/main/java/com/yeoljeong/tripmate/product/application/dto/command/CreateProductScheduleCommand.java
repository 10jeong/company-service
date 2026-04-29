package com.yeoljeong.tripmate.product.application.dto.command;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateProductScheduleCommand {

  private UUID productId;
  private LocalDate startDate;
  private LocalDate endDate;
  private int stock;
}
