package com.yeoljeong.tripmate.product.infrastructure.messaging.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeoljeong.tripmate.event.PlanUnitParticipantAddedEvent;
import com.yeoljeong.tripmate.event.enums.PlanTopic;
import com.yeoljeong.tripmate.exception.BusinessException;
import com.yeoljeong.tripmate.product.application.service.command.ProductScheduleCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlanParticipantAddedEventListener {

  private final ProductScheduleCommandService productScheduleCommandService;
  private final ObjectMapper objectMapper;

  @KafkaListener(
      topics = PlanTopic.PLAN_UNIT_PARTICIPANT_ADDED_TOPIC,
      groupId = "company-service"
  )
  public void handleScheduleParticipantAdded(
      String message,
      Acknowledgment ack
  ) {
    try {
      // 직접 역직렬화
      PlanUnitParticipantAddedEvent event =
          objectMapper.readValue(message, PlanUnitParticipantAddedEvent.class);

      log.info("plan.participant.added 이벤트 수신 - scheduleId={}, quantity={}",
          event.scheduleId(), event.quantity());

      // 재고 차감 서비스 호출
      productScheduleCommandService.deductStock(
          event.productId(),
          event.scheduleId(),
          event.planUnitId(),
          event.userId(),
          event.quantity()
      );

      // TODO: 멱등성 처리 추가 예정

      // 성공 시에만 offset 커밋
      ack.acknowledge();

    } catch (BusinessException e) {
      // 비즈니스 예외는 재시도 불필요 → ack하고 넘어감
      log.warn("[PlanParticipantAddedListener] 비즈니스 예외 - error: {}", e.getMessage());
      ack.acknowledge();
    } catch (Exception e) {
      // 시스템 예외는 재시도
      log.error("[PlanParticipantAddedListener] 처리 실패", e);
      throw new RuntimeException(e);
    }
  }
}