package com.yeoljeong.tripmate.product.infrastructure.persistence.jpa;

import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductScheduleJpaRepository extends JpaRepository<ProductSchedule, UUID> {

}
