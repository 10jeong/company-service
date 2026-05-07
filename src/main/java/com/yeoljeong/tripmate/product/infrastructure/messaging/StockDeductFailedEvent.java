package com.yeoljeong.tripmate.product.infrastructure.messaging;

import java.util.UUID;
//공용모듈로 옮기기
  public record StockDeductFailedEvent(
      UUID eventId,
      UUID productId,
      UUID scheduleId,
      int quantity
  ){}

