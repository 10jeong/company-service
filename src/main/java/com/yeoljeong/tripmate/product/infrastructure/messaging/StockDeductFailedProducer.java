package com.yeoljeong.tripmate.product.infrastructure.messaging;

import com.yeoljeong.tripmate.product.application.port.StockDeductFailedPublisher;
import org.springframework.stereotype.Component;

@Component
public class StockDeductFailedProducer implements StockDeductFailedPublisher {

  @Override
  public void publish(StockDeductFailedEvent event) {
    // Kafka 발행
  }
}
