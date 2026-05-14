package com.yeoljeong.tripmate.product.infrastructure.persistence.repositoryImpl;

import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import com.yeoljeong.tripmate.product.domain.repository.ProductScheduleRepository;
import com.yeoljeong.tripmate.product.infrastructure.persistence.jpa.ProductScheduleJpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductScheduleRepositoryImpl implements ProductScheduleRepository {

  // Spring Data JPA Repository 위임 객체
  private final ProductScheduleJpaRepository jpaRepository;


  // 스케줄 일괄 저장
  @Override
  public List<ProductSchedule> saveAll(List<ProductSchedule> schedules) {
    return jpaRepository.saveAll(schedules);
  }


  // 영속성 컨텍스트 즉시 반영
  // - DB UNIQUE 제약 조건 예외를 즉시 발생시키기 위해 사용
  @Override
  public void flush() {
    jpaRepository.flush();
  }


  // 특정 상품의 스케줄 목록 조회
  @Override
  public Slice<ProductSchedule> findAllByProductId(UUID productId, Pageable pageable) {
    return jpaRepository.findAllByProductId(productId, pageable);
  }

  // 내부 통신용 조회 전용 스케줄 단건 조회 (scheduleId 기준)
  @Override
  public Optional<ProductSchedule> findReadOnlyById(UUID id) {
    return jpaRepository.findReadOnlyById(id);
  }

  // 재고 차감용 조회
  // - 비관적 락
  @Override
  public Optional<ProductSchedule> findByIdAndProductId(UUID id, UUID productId) {
    return jpaRepository.findByIdAndProductId(id, productId);
  }

  // 조회 전용 스케줄 단건 조회
  // - 스케줄 조회 API 용
  // - 내부 통신용 주문에 주는 상품 정보 조회용
  @Override
  public Optional<ProductSchedule> findReadOnlyByIdAndProductId(
      UUID id,
      UUID productId
  ) {
    return jpaRepository.findReadOnlyByIdAndProductId(id, productId);
  }

  // 특정 날짜 예약 가능한 스케줄 조회
  // - ACTIVE 상태
  // - stock > 0
  @Override
  public Slice<ProductSchedule> findAvailableSchedulesByDate(
      LocalDate date,
      Pageable pageable
  ) {
    return jpaRepository.findAvailableSchedulesByDate(date, pageable);
  }

  // 스케줄 ID 리스트로 다건 조회
  // - 내부 통신용 일정에 넘겨주는 상품 스케줄 정보 다건 조회
  @Override
  public List<ProductSchedule> findAllById(List<UUID> scheduleIds) {
    return jpaRepository.findAllById(scheduleIds);
  }
}