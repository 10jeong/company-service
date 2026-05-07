package com.yeoljeong.tripmate.product.domain.repository;

import com.yeoljeong.tripmate.domain.constants.OutboxStatus;
import com.yeoljeong.tripmate.product.domain.outbox.ProductOutbox;
import java.util.List;

public interface ProductOutboxRepository {
  ProductOutbox save(ProductOutbox outbox);
  List<ProductOutbox> findAllByStatus(OutboxStatus status);
}
