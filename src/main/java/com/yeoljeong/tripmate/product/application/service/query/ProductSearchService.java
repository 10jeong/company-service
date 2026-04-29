package com.yeoljeong.tripmate.product.application.service.query;

import com.yeoljeong.tripmate.product.application.dto.result.ProductAvailabilityResult;
import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import com.yeoljeong.tripmate.product.domain.repository.ProductScheduleRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 날짜 기준으로 판매 가능한 상품 조회
//ex ) 5월 28일 -> 박물관 입장권 , 성당 입장권 , 낙타 투어  ..
@Service
@RequiredArgsConstructor
public class ProductSearchService {

  private final ProductScheduleRepository scheduleRepository;

  // 날짜 기준으로 예약 가능한 상품 조회
  public List<ProductAvailabilityResult> getAvailableProducts(LocalDate date) {
    // fetch join으로 한번에 조회 (N+1 없음)
    List<ProductSchedule> schedules =
        scheduleRepository.findAvailableSchedulesByDate(date);

    return schedules.stream()
        .map(ProductAvailabilityResult::from)
        .toList();
  }
}
