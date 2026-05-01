package com.yeoljeong.tripmate.product.application.service.command;

import com.yeoljeong.tripmate.exception.BusinessException;
import com.yeoljeong.tripmate.product.domain.exception.ProductErrorCode;
import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import com.yeoljeong.tripmate.product.domain.repository.ProductScheduleRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductScheduleStockService {

  private final ProductScheduleRepository scheduleRepository;

  @Transactional
  public void deductStock(UUID productId, UUID scheduleId, int quantity) {
    ProductSchedule schedule = scheduleRepository
        .findByIdAndProductId(scheduleId, productId)
        .orElseThrow(() -> new BusinessException(ProductErrorCode.SCHEDULE_NOT_FOUND));

    schedule.decreaseStock(quantity);  // 도메인 메서드 호출
  }
}
