package com.yeoljeong.tripmate.product.application.service.query;

import com.yeoljeong.tripmate.exception.BusinessException;
import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleInfoResult;
import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleQueryResult;
import com.yeoljeong.tripmate.product.domain.exception.ProductErrorCode;
import com.yeoljeong.tripmate.product.domain.model.Product;
import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import com.yeoljeong.tripmate.product.domain.repository.ProductRepository;
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
  private final ProductRepository productRepository;

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
  public Slice<ProductScheduleInfoResult> getAvailableProducts(
      LocalDate date,
      Pageable pageable
  ) {
    return scheduleRepository.findAvailableSchedulesByDate(date, pageable)
        .map(schedule -> {
          Product product = findProduct(schedule.getProductId());
          return ProductScheduleInfoResult.from(product, schedule);
        });
  }

  //(내부통신용) 주문시 사용 하는 상품,스케줄 정보
  public ProductScheduleInfoResult getProductSchedule(UUID productId, UUID scheduleId) {

    ProductSchedule schedule = scheduleRepository
        .findReadOnlyByIdAndProductId(scheduleId, productId)
        .orElseThrow(() -> new BusinessException(ProductErrorCode.SCHEDULE_NOT_FOUND));

    Product product = findProduct(schedule.getProductId());
    return ProductScheduleInfoResult.from(product, schedule);
  }

  // (내부통신용) 일정 확정시 사용하는 상품,스케줄 정보
  public ProductScheduleInfoResult getSchedule(UUID scheduleId) {

    ProductSchedule schedule = scheduleRepository
        .findReadOnlyById(scheduleId)
        .orElseThrow(() -> new BusinessException(ProductErrorCode.SCHEDULE_NOT_FOUND));

    Product product = findProduct(schedule.getProductId());
    return ProductScheduleInfoResult.from(product, schedule);
  }

  private Product findProduct(UUID productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> new BusinessException(ProductErrorCode.PRODUCT_NOT_FOUND));
  }

}