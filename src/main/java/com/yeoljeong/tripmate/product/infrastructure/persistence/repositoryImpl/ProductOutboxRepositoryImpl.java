package com.yeoljeong.tripmate.product.infrastructure.persistence.repositoryImpl;

import com.yeoljeong.tripmate.domain.constants.OutboxStatus;
import com.yeoljeong.tripmate.product.domain.outbox.ProductOutbox;
import com.yeoljeong.tripmate.product.domain.repository.ProductOutboxRepository;
import com.yeoljeong.tripmate.product.infrastructure.persistence.jpa.ProductOutboxJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductOutboxRepositoryImpl implements ProductOutboxRepository {

  private final ProductOutboxJpaRepository productOutboxJpaRepository;

  @Override
  public ProductOutbox save(ProductOutbox outbox) {
    return productOutboxJpaRepository.save(outbox);
  }

  @Override
  public List<ProductOutbox> findAllByStatus(OutboxStatus status) {
    return productOutboxJpaRepository.findAllByStatus(status);
  }
}
