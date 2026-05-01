package com.yeoljeong.tripmate.product.infrastructure.messaging;

import com.yeoljeong.tripmate.event.enums.PlanTopic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlanParticipantAddedEventListener {

  @KafkaListener(
      topics = PlanTopic.PLAN_PARTICIPANT_ADDED_TOPIC,
      groupId = "company-service"
  )
  public void handleScheduleParticipantAdded(
      ScheduleParticipantAddedEvent event
  ) {

    log.info("plan.participant.added 이벤트 수신 - scheduleId={}, quantity={}",
        event.scheduleId(),
        event.quantity()
    );

    //TODO 재고 차감 로직
    // 1. 중복 처리 방지
    // 2. 재고 차감 서비스 호출
    // 3. 처리 완료 기록
    // 4. offset 커밋

  }
}
