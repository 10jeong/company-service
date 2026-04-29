package com.yeoljeong.tripmate.product.infrastructure.persistence.jpa;

import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductScheduleJpaRepository extends JpaRepository<ProductSchedule, UUID> {
  Slice<ProductSchedule> findAllBy(Pageable pageable);

  Slice<ProductSchedule> findAllByProductId(UUID productId, Pageable pageable);

  Optional<ProductSchedule> findByIdAndProductId(UUID id, UUID productId);


  /**
   * 특정 날짜에 예약 가능한 스케줄 조회
   * - status = ACTIVE, stock > 0 인 경우만 조회
   * - N+1 방지를 위해 product fetch join
   */

  @Query("""
select s
from ProductSchedule s
join fetch s.product p
where s.date = :date
  and s.status = com.yeoljeong.tripmate.product.domain.enums.ScheduleStatus.ACTIVE
  and s.stock > 0
""")
  List<ProductSchedule> findAvailableSchedulesByDate(@Param("date") LocalDate date);
}
