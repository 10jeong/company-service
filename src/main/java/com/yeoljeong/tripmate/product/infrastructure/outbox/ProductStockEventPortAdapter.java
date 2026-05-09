package com.yeoljeong.tripmate.product.infrastructure.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeoljeong.tripmate.event.ProductStockDeductFailedEvent;
import com.yeoljeong.tripmate.event.enums.ProductTopic;
import com.yeoljeong.tripmate.product.application.port.ProductStockEventPort;
import com.yeoljeong.tripmate.product.infrastructure.persistence.jpa.ProductOutboxJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductStockEventPortAdapter implements ProductStockEventPort {

  private final ObjectMapper objectMapper;
  private final ProductOutboxJpaRepository productOutboxJpaRepository;

  //REQUIRES_NEW로 deductStock 과 별도의 트랜잭션
  //deductStock 이 롤백 되어도 이 저장은 유지됨
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void save(UUID planUnitId, UUID userId, UUID orderId, int quantity) {
    try {
      String payload = objectMapper.writeValueAsString(
          new ProductStockDeductFailedEvent(
              UUID.randomUUID(),
              orderId,
              planUnitId,
              userId,
              quantity
          )
      );
      // ProductOutbox.create() 내부에서 PENDING으로 초기화됨
      productOutboxJpaRepository.save(
          ProductOutbox.create(ProductTopic.STOCK_DEDUCT_FAILED_TOPIC, payload)
      );
    } catch (JsonProcessingException ex) {
      throw new RuntimeException("보상 이벤트 직렬화 실패", ex);
    }
  }
}