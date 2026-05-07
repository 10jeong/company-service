package com.yeoljeong.tripmate.product.application.port;

import com.yeoljeong.tripmate.product.infrastructure.messaging.StockDeductFailedEvent;

public interface StockDeductFailedPublisher {
  void publish(StockDeductFailedEvent event);
}
