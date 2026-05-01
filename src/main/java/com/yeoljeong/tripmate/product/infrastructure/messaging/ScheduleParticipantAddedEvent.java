package com.yeoljeong.tripmate.product.infrastructure.messaging;

import java.util.UUID;

public record ScheduleParticipantAddedEvent(
    UUID eventId,
    UUID productId,
    UUID scheduleId,
    int quantity
) {
}