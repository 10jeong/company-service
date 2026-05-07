package com.yeoljeong.tripmate.product.infrastructure.persistence.jpa;

import com.yeoljeong.tripmate.domain.constants.OutboxStatus;
import com.yeoljeong.tripmate.product.domain.outbox.ProductOutbox;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOutboxJpaRepository extends JpaRepository<ProductOutbox, UUID> {
  List<ProductOutbox> findAllByStatus(OutboxStatus status);
}
