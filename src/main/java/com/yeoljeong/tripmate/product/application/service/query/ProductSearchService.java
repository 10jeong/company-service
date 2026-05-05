package com.yeoljeong.tripmate.product.application.service.query;

import com.yeoljeong.tripmate.exception.BusinessException;
import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleInfoResult;
import com.yeoljeong.tripmate.product.domain.exception.ProductErrorCode;
import com.yeoljeong.tripmate.product.domain.repository.ProductScheduleRepository;
import java.time.LocalDate;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

// 날짜 기준으로 판매 가능한 상품 조회
//ex ) 5월 28일 -> 박물관 입장권 , 성당 입장권 , 낙타 투어  ..
@Service
@RequiredArgsConstructor
public class ProductSearchService {

  private final ProductScheduleRepository scheduleRepository;

  // 날짜 기준으로 예약 가능한 상품 조회
  public Slice<ProductScheduleInfoResult> getAvailableProducts(LocalDate date, Pageable pageable) {
    return scheduleRepository.findAvailableSchedulesByDate(date, pageable)
        .map(ProductScheduleInfoResult::from);
  }


  // 검증 포함 조회 (productId + scheduleId)
  // (내부통신용) 주문시 사용 하는 상품,스케줄 정보
  public ProductScheduleInfoResult getProductSchedule(UUID productId, UUID scheduleId) {

    return scheduleRepository
        .findReadOnlyByIdAndProductId(scheduleId, productId)
        .map(ProductScheduleInfoResult::from)
        .orElseThrow(() -> new BusinessException(ProductErrorCode.SCHEDULE_NOT_FOUND));
  }

  // scheduleId 단건 조회
  // (내부통신용) 일정 확정시 사용하는 상품,스케줄 정보
  public ProductScheduleInfoResult getSchedule(UUID scheduleId) {

    return scheduleRepository
        .findReadOnlyById(scheduleId)
        .map(ProductScheduleInfoResult::from)
        .orElseThrow(() -> new BusinessException(ProductErrorCode.SCHEDULE_NOT_FOUND));
  }
}
