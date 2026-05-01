package com.yeoljeong.tripmate.product.presentation.controller.internal;

import com.yeoljeong.tripmate.product.application.service.query.ProductQueryService;
import com.yeoljeong.tripmate.product.application.service.query.ProductSearchService;
import com.yeoljeong.tripmate.product.presentation.dto.response.ProductPlanResponse;
import com.yeoljeong.tripmate.product.presentation.dto.response.ProductScheduleInfoResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/products")
public class ProductFeignClientController {

  private final ProductQueryService productQueryService;
  private final ProductSearchService productSearchService;

  //일정 확정시 사용 하는 상품 정보
  @GetMapping("/{productId}")
  public ProductPlanResponse getProduct(
      @PathVariable UUID productId
  ) {
    return ProductPlanResponse.from(
        productQueryService.getProduct(productId)
    );
  }

  //주문시 사용 하는 상품,스케줄 정보
  @GetMapping("/{productId}/schedules/{scheduleId}")
  public ProductScheduleInfoResponse getSchedule(
      @PathVariable UUID productId,
      @PathVariable UUID scheduleId
  ) {
    return ProductScheduleInfoResponse.from(
        productSearchService.getProductSchedule(productId, scheduleId)
    );
  }
}