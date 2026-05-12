package com.yeoljeong.tripmate.product.infrastructure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeoljeong.tripmate.event.PlanUnitDeductParticipantByOrderEvent;
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
public class PlanParticipantDeductedByOrderEventListener {

  private final ProductScheduleCommandService productScheduleCommandService;
  private final ObjectMapper objectMapper;

  @KafkaListener(
      topics = PlanTopic.PLAN_UNIT_PARTICIPANT_DEDUCTED_BY_ORDER_TOPIC,
      groupId = "company-service"
  )
  public void handleParticipantDeductedByOrder(
      String message,
      Acknowledgment ack
  ) {
    try {
      PlanUnitDeductParticipantByOrderEvent event =
          objectMapper.readValue(message, PlanUnitDeductParticipantByOrderEvent.class);

      log.info("plan.unit.participant.deducted-by-order 이벤트 수신 - scheduleId={}, quantity={}",
          event.scheduleId(), event.quantity());

      // 재고 복구 서비스 호출
      productScheduleCommandService.increaseStock(
          event.productId(),
          event.scheduleId(),
          event.quantity()
      );

      ack.acknowledge();

    } catch (BusinessException e) {
      log.warn("[PlanParticipantDeductedByOrderListener] 비즈니스 예외 - error: {}", e.getMessage());
      ack.acknowledge();
    } catch (Exception e) {
      log.error("[PlanParticipantDeductedByOrderListener] 처리 실패", e);
      throw new RuntimeException(e);
    }
  }
}
