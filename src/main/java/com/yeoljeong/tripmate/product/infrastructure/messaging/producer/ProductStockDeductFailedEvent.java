package com.yeoljeong.tripmate.product.infrastructure.messaging.producer;

import java.util.UUID;
//공용모듈로 옮기기
  public record ProductStockDeductFailedEvent(
      UUID eventId,
      UUID productId,
      UUID scheduleId,
      int quantity
  ){}

