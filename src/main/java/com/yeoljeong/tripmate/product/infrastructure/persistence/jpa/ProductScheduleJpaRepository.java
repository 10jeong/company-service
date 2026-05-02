package com.yeoljeong.tripmate.product.infrastructure.persistence.jpa;

import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import jakarta.persistence.LockModeType;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductScheduleJpaRepository
    extends JpaRepository<ProductSchedule, UUID> {

  // 전체 스케줄 목록 조회
  Slice<ProductSchedule> findAllBy(Pageable pageable);

  // 특정 상품의 스케줄 목록 조회
  Slice<ProductSchedule> findAllByProductId(
      UUID productId,
      Pageable pageable
  );

  // 조회 전용 스케줄 단건 조회
  // - 락 없음
  // - readOnly transaction 에서 사용
  // - 스케줄 조회 API 용도
  Optional<ProductSchedule> findReadOnlyByIdAndProductId(
      UUID id,
      UUID productId
  );

  // 재고 차감/주문 내부 통신용 조회
  // - 비관적 락 사용
  // - 주문 내부 통신용
  // - 동시에 여러 예약 요청이 들어 와도 재고 정합성 보장
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<ProductSchedule> findByIdAndProductId(
      UUID id,
      UUID productId
  );

   // 특정 날짜 예약 가능한 스케줄 조회
   //- status = ACTIVE
   // - stock > 0 인 경우만 조회
   // - N+1 방지를 위해 product fetch join 사용
  @Query("""
      SELECT s
      FROM ProductSchedule s
      JOIN FETCH s.product p
      WHERE s.date = :date
      AND s.status = com.yeoljeong.tripmate.product.domain.enums.ScheduleStatus.ACTIVE
      AND s.stock > 0
      """)
  Slice<ProductSchedule> findAvailableSchedulesByDate(
      @Param("date") LocalDate date,
      Pageable pageable
  );
}