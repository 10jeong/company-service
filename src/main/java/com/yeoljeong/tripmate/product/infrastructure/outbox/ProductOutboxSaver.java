package com.yeoljeong.tripmate.product.infrastructure.outbox;

import com.yeoljeong.tripmate.product.domain.outbox.ProductOutbox;
import com.yeoljeong.tripmate.product.domain.repository.ProductOutboxRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;

@Component
@RequiredArgsConstructor
public class ProductOutboxSaver {

  private final ProductOutboxRepository productOutboxRepository;

  //outboxSaver는 REQUIRES_NEW로 deductStock 과 별도의 트랜잭션
  //deductStock 이 롤백 되어도 이 저장은 유지됨
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void save(String topic, String payload) {
    // ProductOutbox.create() 내부에서 PENDING으로 초기화됨
    productOutboxRepository.save(ProductOutbox.create(topic, payload));
  }
}
