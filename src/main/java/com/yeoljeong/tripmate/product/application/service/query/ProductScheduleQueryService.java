package com.yeoljeong.tripmate.product.application.service.query;

import com.yeoljeong.tripmate.exception.BusinessException;
import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleInfoResult;
import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleQueryResult;
import com.yeoljeong.tripmate.product.domain.exception.ProductErrorCode;
import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import java.time.LocalDate;
import org.springframework.transaction.annotation.Transactional;
import com.yeoljeong.tripmate.product.domain.repository.ProductScheduleRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductScheduleQueryService {

  private final ProductScheduleRepository scheduleRepository;

  // 단건 조회
  public ProductScheduleQueryResult getSchedule(UUID productId, UUID scheduleId) {

    ProductSchedule schedule =
        scheduleRepository.findReadOnlyByIdAndProductId(scheduleId, productId)
            .orElseThrow(() -> new BusinessException(ProductErrorCode.SCHEDULE_NOT_FOUND));

    return ProductScheduleQueryResult.from(schedule);
  }

  // 목록 조회
  public Slice<ProductScheduleQueryResult> getSchedules(UUID productId, Pageable pageable) {
    return scheduleRepository.findAllByProductId(productId, pageable)
        .map(ProductScheduleQueryResult::from);
  }

  // 날짜 기준 예약 가능한 상품 조회
  //ex ) 5월 28일 -> 박물관 입장권 , 성당 입장권 , 낙타 투어  ..
  public Slice<ProductScheduleInfoResult> getAvailableProducts(
      LocalDate date,
      Pageable pageable
  ) {
    return scheduleRepository.findAvailableSchedulesByDate(date, pageable)
        .map(ProductScheduleInfoResult::from);
  }

  //(내부통신용) 주문시 사용 하는 상품,스케줄 정보
  //검증 포함 조회 (productId + scheduleId)
  public ProductScheduleInfoResult getProductSchedule(UUID productId, UUID scheduleId) {

    return scheduleRepository
        .findReadOnlyByIdAndProductId(scheduleId, productId)
        .map(ProductScheduleInfoResult::from)
        .orElseThrow(() -> new BusinessException(ProductErrorCode.SCHEDULE_NOT_FOUND));
  }

  // (내부통신용) 일정 확정시 사용하는 상품,스케줄 정보
  // scheduleId 단건 조회
  public ProductScheduleInfoResult getSchedule(UUID scheduleId) {

    return scheduleRepository
        .findReadOnlyById(scheduleId)
        .map(ProductScheduleInfoResult::from)
        .orElseThrow(() -> new BusinessException(ProductErrorCode.SCHEDULE_NOT_FOUND));
  }

}
