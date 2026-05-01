package com.yeoljeong.tripmate.product.infrastructure.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlanParticipantAddedEventListener {

  @KafkaListener(
      topics = "plan.participant.added",
      groupId = "company-service"
  )
  public void handleScheduleParticipantAdded(
      ScheduleParticipantAddedEvent event
  ) {

    log.info("plan.participant.added 이벤트 수신 - scheduleId={}, quantity={}",
        event.scheduleId(),
        event.quantity()
    );

    // TODO
    // 재고 차감 로직 연결 예정
    // 중복 처리(idempotency) 적용 예정

  }
}
