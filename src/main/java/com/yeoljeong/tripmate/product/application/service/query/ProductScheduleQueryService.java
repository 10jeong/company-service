package com.yeoljeong.tripmate.product.application.service.query;

import com.yeoljeong.tripmate.exception.BusinessException;
import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleInfoResult;
import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleQueryResult;
import com.yeoljeong.tripmate.product.domain.exception.ProductErrorCode;
import com.yeoljeong.tripmate.product.domain.model.Product;
import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import com.yeoljeong.tripmate.product.domain.repository.ProductRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
    // 1. 날짜 기준 예약 가능한 스케줄 목록 조회
    Slice<ProductSchedule> schedules =
        scheduleRepository.findAvailableSchedulesByDate(date, pageable);

    // 2. 스케줄에 연관된 상품을 한번에 조회 (N+1 방지)
    Map<UUID, Product> productMap = getProductMap(schedules.getContent());

    // 3. 스케줄 + 상품 정보 조합해서 반환
    return schedules.map(schedule ->
        ProductScheduleInfoResult.from(productMap.get(schedule.getProductId()), schedule)
    );
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

  public List<ProductScheduleInfoResult> getSchedules(List<UUID> scheduleIds) {
    List<ProductSchedule> schedules = scheduleRepository.findAllById(scheduleIds);
    Map<UUID, Product> productMap = getProductMap(schedules); // 이미 있는 메서드 재활용!

    return schedules.stream()
        .map(schedule -> ProductScheduleInfoResult.from(
            productMap.get(schedule.getProductId()), schedule))
        .toList();
  }


  /**메서드**/
// 스케줄 목록에서 productId 추출 후 상품 한번에 조회
// - 스케줄마다 개별 조회(N+1) 대신 in절로 한번에 조회
  private Map<UUID, Product> getProductMap(List<ProductSchedule> schedules) {
    // 1. 스케줄 목록에서 productId만 추출
    List<UUID> productIds = schedules.stream()
        .map(ProductSchedule::getProductId)
        .toList();

    // 2. productId 목록으로 상품 한번에 조회 (WHERE id IN (...))
    List<Product> products = productRepository.findAllById(productIds);

    // 3. productId를 key, Product를 value로 Map 변환 (빠른 탐색을 위해)
    return products.stream()
        .collect(Collectors.toMap(Product::getId, p -> p));
  }

}