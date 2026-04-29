package com.yeoljeong.tripmate.product.application.dto.command;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateProductScheduleCommand {

  //ProductSchedule은 Product 객체가 필요해서 서비스에서 조회 후 생성하는 구조
  private UUID productId;
  private LocalDate startDate;
  private LocalDate endDate;
  private int stock;
}
