package com.yeoljeong.tripmate.product.infrastructure.persistence.jpa;

import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductScheduleJpaRepository extends JpaRepository<ProductSchedule, UUID> {
  Slice<ProductSchedule> findAllBy(Pageable pageable);

  Slice<ProductSchedule> findAllByProductId(UUID productId, Pageable pageable);

  Optional<ProductSchedule> findByIdAndProductId(UUID id, UUID productId);
}
