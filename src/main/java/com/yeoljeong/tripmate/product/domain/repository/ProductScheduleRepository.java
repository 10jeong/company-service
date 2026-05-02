package com.yeoljeong.tripmate.product.domain.repository;

import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ProductScheduleRepository {

  // 단일 스케줄 저장
  ProductSchedule save(ProductSchedule schedule);

  // 스케줄 일괄 저장
  List<ProductSchedule> saveAll(List<ProductSchedule> schedules);

  // 스케줄 단건 조회 (기본 조회)
  Optional<ProductSchedule> findById(UUID id);

  // 전체 스케줄 목록 조회
  Slice<ProductSchedule> findAll(Pageable pageable);

  // 영속성 컨텍스트 즉시 반영
  // - DB UNIQUE 제약 예외를 현재 트랜잭션에서 바로 감지하기 위해 사용
  void flush();

  // 특정 상품의 스케줄 목록 조회
  Slice<ProductSchedule> findAllByProductId(UUID productId, Pageable pageable);

  // 조회 전용 스케줄 단건 조회
  // - 락 없음
  // - readOnly transaction 에서 사용
  // - 스케줄 조회 API 용도
  Optional<ProductSchedule> findReadOnlyByIdAndProductId(
      UUID id,
      UUID productId
  );

  // 재고 차감/예약 처리용 조회
  // - 비관적 락 사용
  // - 동시성 제어 목적
  Optional<ProductSchedule> findByIdAndProductId(UUID id, UUID productId);

  // 특정 날짜 예약 가능한 스케줄 조회
  // - ACTIVE 상태
  // - stock > 0
  Slice<ProductSchedule> findAvailableSchedulesByDate(
      LocalDate date,
      Pageable pageable
  );

}