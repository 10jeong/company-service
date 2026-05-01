package com.yeoljeong.tripmate.product.infrastructure.messaging;

import com.yeoljeong.tripmate.event.enums.PlanTopic;
import com.yeoljeong.tripmate.product.application.service.command.ProductScheduleStockService;
import com.yeoljeong.tripmate.product.infrastructure.messaging.ScheduleParticipantAddedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlanParticipantAddedEventListener {

  private final ProductScheduleStockService stockService;

  @KafkaListener(
      topics = PlanTopic.PLAN_PARTICIPANT_ADDED_TOPIC,
      groupId = "company-service"
  )
  public void handleScheduleParticipantAdded(
      ScheduleParticipantAddedEvent event,
      Acknowledgment ack
  ) {
    log.info("plan.participant.added 이벤트 수신 - scheduleId={}, quantity={}",
        event.scheduleId(), event.quantity());

    // 재고 차감 서비스 호출
    stockService.deductStock(event.productId(), event.scheduleId(), event.quantity());

    // TODO: MVP 이후 멱등성 처리 추가 예정 (ProcessedEvent 테이블로 중복 방지)

    // offset 커밋
    ack.acknowledge();
  }
}