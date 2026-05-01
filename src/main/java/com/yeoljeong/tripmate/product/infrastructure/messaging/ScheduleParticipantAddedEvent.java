package com.yeoljeong.tripmate.product.infrastructure.messaging;

import java.util.UUID;

public record ScheduleParticipantAddedEvent(
    UUID eventId,
    UUID productId,
    UUID scheduleId,
    int quantity
) {
  public ScheduleParticipantAddedEvent {
        if (eventId == null || productId == null || scheduleId == null) {
            throw new IllegalArgumentException("eventId, productId, scheduleId는 null일 수 없습니다.");
         }
      if (quantity <= 0) {
         throw new IllegalArgumentException("quantity는 1 이상이어야 합니다.");
          }
   }
}