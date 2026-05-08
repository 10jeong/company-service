package com.yeoljeong.tripmate.product.infrastructure.messaging.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeoljeong.tripmate.event.PaymentRefundedEvent;
import com.yeoljeong.tripmate.event.enums.PaymentTopic;
import com.yeoljeong.tripmate.product.application.service.command.ProductScheduleCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentRefundedEventListener {

  private final ProductScheduleCommandService productScheduleCommandService;
  private final ObjectMapper objectMapper;

  @KafkaListener(
      topics = PaymentTopic.PAYMENT_REFUNDED_TOPIC,
      groupId = "company-service"
  )
  public void handlePaymentRefunded(
      String message,
      Acknowledgment ack
  ) {
    try {
      // 직접 역직렬화
      PaymentRefundedEvent event =
          objectMapper.readValue(message, PaymentRefundedEvent.class);

      log.info("payment.refunded 이벤트 수신 - scheduleId={}, quantity={}",
          event.scheduleId(), event.quantity());

      // 재고 추가 서비스 호출
      productScheduleCommandService.increaseStock(
          event.productId(),
          event.scheduleId(),
          event.quantity()
      );

      // 성공 시에만 offset 커밋
      ack.acknowledge();

    } catch (Exception e) {
      log.error("PaymentRefundedEvent 처리 실패", e);
      throw new RuntimeException(e);
    }
  }
}
