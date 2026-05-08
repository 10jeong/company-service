package com.yeoljeong.tripmate.product.infrastructure.outbox;

import com.yeoljeong.tripmate.domain.constants.OutboxStatus;
import com.yeoljeong.tripmate.product.domain.outbox.ProductOutbox;
import com.yeoljeong.tripmate.product.infrastructure.persistence.jpa.ProductOutboxJpaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductOutboxDispatcher {

  private final ProductOutboxJpaRepository productOutboxJpaRepository;
  private final KafkaTemplate<String, String> kafkaTemplate;

  // 1초마다 자동 실행
  @Scheduled(fixedDelay = 1000)
  @Transactional
  public void dispatch() {
    // DB에서 PENDING인 것들 꺼내서 Kafka로 발행
    List<ProductOutbox> pendingEvents =
        productOutboxJpaRepository.findAllByStatus(OutboxStatus.PENDING);

    pendingEvents.forEach(outbox -> {
      //성공 시 PUBLISHED로 변경
      try {
        // 전송 완료될 때까지 기다림
        kafkaTemplate.send(outbox.getTopic(), outbox.getPayload()).get();
        // 전송 성공 확인 후 PUBLISHED
        outbox.published();

        //실패 시 retryCount++, 3회 초과 시 FAILED
      } catch (Exception e) {
        log.error("Outbox 발행 실패 - topic={}", outbox.getTopic(), e);
        outbox.fail();
      }
    });
  }
}