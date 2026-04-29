package com.yeoljeong.tripmate.product.application.service.command;

import com.yeoljeong.tripmate.exception.BusinessException;
import com.yeoljeong.tripmate.product.application.dto.command.CreateProductScheduleCommand;
import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleResult;
import com.yeoljeong.tripmate.product.domain.exception.ProductErrorCode;
import com.yeoljeong.tripmate.product.domain.model.Product;
import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import com.yeoljeong.tripmate.product.domain.repository.ProductRepository;
import com.yeoljeong.tripmate.product.domain.repository.ProductScheduleRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

//Product: DTO → toEntity → save
//Schedule: DTO → Service 로직 → 여러 Entity 생성 → saveAll

@Service
@RequiredArgsConstructor
public class ProductScheduleCommandService {

  private final ProductRepository productRepository;
  private final ProductScheduleRepository scheduleRepository;

  /**
   * 상품 스케줄 일괄 생성
   * - 상품 조회
   * - 날짜 유효성 검증
   * - 날짜 범위 기반 스케줄 생성
   * - 일괄 저장 (중복은 DB UNIQUE 로 처리)
   */

  //상품 스케줄 일괄 생성
  @Transactional
  public ProductScheduleResult createSchedules(CreateProductScheduleCommand command) {

    Product product = findProduct(command.getProductId());
    validateDate(command);
    List<ProductSchedule> schedules = createSchedulesByDateRange(product, command);

    saveSchedules(schedules);

    // 생성 결과 반환
    return ProductScheduleResult.of(
        product.getId(),
        schedules.size(),
        command.getStartDate(),
        command.getEndDate()
    );
  }


  // ==메서드==

  // 상품 조회
  private Product findProduct(UUID productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> new BusinessException(ProductErrorCode.PRODUCT_NOT_FOUND));
  }

  // 날짜 유효성 검증
  private void validateDate(CreateProductScheduleCommand command) {

    LocalDate startDate = command.getStartDate();
    LocalDate endDate = command.getEndDate();

    // null 체크
    if (startDate == null || endDate == null) {
      throw new BusinessException(ProductErrorCode.INVALID_DATE);
    }

    // 시작일이 종료일보다 늦을 수 없음
    if (startDate.isAfter(endDate)) {
      throw new BusinessException(ProductErrorCode.INVALID_DATE);
    }

    // inclusive 기준 최대 31일 제한
    long daysInclusive = ChronoUnit.DAYS.between(startDate, endDate) + 1;

    if (daysInclusive > 31) {
      throw new BusinessException(ProductErrorCode.INVALID_DATE_RANGE);
    }
  }

  // 날짜 범위 기반 스케줄 생성 (startDate ~ endDate까지 하루 단위로 생성)
  private List<ProductSchedule> createSchedulesByDateRange(
      Product product,
      CreateProductScheduleCommand command
  ) {

    List<ProductSchedule> schedules = new ArrayList<>();

    // 순회 시작점 = startDate
    LocalDate current = command.getStartDate();

    // startDate부터 endDate까지 하루씩 증가하며 스케줄 생성
    while (!current.isAfter(command.getEndDate())) {
      schedules.add( ProductSchedule.create(product, current, command.getStock()) );
      current = current.plusDays(1); // 다음 날짜로 이동
    }

    return schedules;
  }

  // 스케줄 일괄 저장
  private void saveSchedules(List<ProductSchedule> schedules) {
    try {
      scheduleRepository.saveAll(schedules);
      scheduleRepository.flush(); //추가: flush 해야 예외를 여기서 잡을 수 있음
    } catch (DataIntegrityViolationException e) {
      //(product_id + date) UNIQUE 제약 위반 시 예외 변환
      throw new BusinessException(ProductErrorCode.SCHEDULE_ALREADY_EXISTS);
    }
  }
}
