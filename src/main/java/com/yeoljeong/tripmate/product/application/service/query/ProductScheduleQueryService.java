package com.yeoljeong.tripmate.product.application.service.query;

import com.yeoljeong.tripmate.exception.BusinessException;
import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleQueryResult;
import com.yeoljeong.tripmate.product.domain.exception.ProductErrorCode;
import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import com.yeoljeong.tripmate.product.domain.repository.ProductScheduleRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductScheduleQueryService {

  private final ProductScheduleRepository scheduleRepository;

  // 단건 조회
  public ProductScheduleQueryResult getSchedule(UUID scheduleId) {
    ProductSchedule schedule = scheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new BusinessException(ProductErrorCode.SCHEDULE_NOT_FOUND));

    return ProductScheduleQueryResult.from(schedule);
  }

  // 목록 조회
  public Slice<ProductScheduleQueryResult> getSchedules(Pageable pageable) {
    return scheduleRepository.findAll(pageable)
        .map(ProductScheduleQueryResult::from);
  }
}
