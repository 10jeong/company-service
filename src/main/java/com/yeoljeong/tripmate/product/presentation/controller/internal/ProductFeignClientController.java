package com.yeoljeong.tripmate.product.presentation.controller.internal;

import com.yeoljeong.tripmate.product.application.service.query.ProductQueryService;
import com.yeoljeong.tripmate.product.application.service.query.ProductScheduleQueryService;
import com.yeoljeong.tripmate.product.presentation.dto.response.ProductScheduleInfoResponse;
import com.yeoljeong.tripmate.product.presentation.dto.response.ProductScheduleSimpleResponse;
import com.yeoljeong.tripmate.product.presentation.dto.response.WithdrawalCheckResponse;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/products")
public class ProductFeignClientController {

  private final ProductScheduleQueryService productScheduleQueryService;
  private final ProductQueryService productQueryService;

  // 일정 확정시 사용 하는 상품 정보
  @GetMapping("/schedules/{scheduleId}")
  public ProductScheduleInfoResponse getSchedule(
      @PathVariable UUID scheduleId
  ) {
    return ProductScheduleInfoResponse.from(
        productScheduleQueryService.getSchedule(scheduleId)
    );
  }

  //일정에게 넘겨주는 리스트 정보
  @GetMapping("/schedules")
  public List<ProductScheduleSimpleResponse> getSchedules(
      @RequestParam List<UUID> scheduleIds
  ) {
    return productScheduleQueryService.getSchedules(scheduleIds)
        .stream()
        .map(ProductScheduleSimpleResponse::from)
        .toList();
  }

  // 주문시 사용 하는 상품, 스케줄 정보
  @GetMapping("/{productId}/schedules/{scheduleId}")
  public ProductScheduleInfoResponse getProductSchedule(
      @PathVariable UUID productId,
      @PathVariable UUID scheduleId
  ) {
    return ProductScheduleInfoResponse.from(
        productScheduleQueryService.getProductSchedule(productId, scheduleId)
    );
  }
  // 회원 탈퇴 가능 여부 확인
  @GetMapping("/withdrawal-check")
  public WithdrawalCheckResponse withdrawalCheck(
      @RequestParam UUID userId
  ) {
    boolean hasActiveProduct = productQueryService.hasActiveProduct(userId);
    return new WithdrawalCheckResponse(hasActiveProduct);
  }

}