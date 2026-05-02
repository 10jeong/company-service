package com.yeoljeong.tripmate.product.application.service.query;

import com.yeoljeong.tripmate.exception.BusinessException;
import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleQueryResult;
import com.yeoljeong.tripmate.product.domain.exception.ProductErrorCode;
import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
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
}
