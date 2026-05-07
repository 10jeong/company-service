package com.yeoljeong.tripmate.product.infrastructure.messaging.producer.handler;

import com.yeoljeong.tripmate.product.application.port.ProductStockDeductFailedPublisher;
import com.yeoljeong.tripmate.product.infrastructure.messaging.producer.ProductStockDeductFailedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ProductStockDeductFailedEventHandler {

  private final ProductStockDeductFailedPublisher publisher;

  @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
  public void handle(ProductStockDeductFailedEvent event) {
    publisher.publish(event);
  }
}
