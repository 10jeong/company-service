package com.yeoljeong.tripmate.product.infrastructure.messaging.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import com.yeoljeong.tripmate.product.application.port.ProductStockDeductFailedPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductStockDeductFailedProducer implements ProductStockDeductFailedPublisher {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Override
  public void publish(ProductStockDeductFailedEvent event) {
    kafkaTemplate.send(
            ProductTopic.STOCK_DEDUCT_FAILED_TOPIC,
            event.scheduleId().toString(),
            event
        )

        .whenComplete((result, ex) -> {
          if (ex != null) {
            log.error("product.stock.deduct.failed 발행 실패 - eventId={}, productId={}, scheduleId={}",
                event.eventId(), event.productId(), event.scheduleId(), ex);
          }
        });
  }
}
