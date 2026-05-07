package com.yeoljeong.tripmate.product.application.port;

import com.yeoljeong.tripmate.product.infrastructure.messaging.producer.ProductStockDeductFailedEvent;

public interface ProductStockDeductFailedPublisher {
  void publish(ProductStockDeductFailedEvent event);
}
