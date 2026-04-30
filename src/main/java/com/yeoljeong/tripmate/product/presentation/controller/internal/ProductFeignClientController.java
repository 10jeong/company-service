package com.yeoljeong.tripmate.product.presentation.controller.internal;

import com.yeoljeong.tripmate.product.application.service.query.ProductSearchService;
import com.yeoljeong.tripmate.product.presentation.dto.response.ProductAvailabilityResponse;
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

  private final ProductSearchService productSearchService;

  @GetMapping("/{productId}/schedules/{scheduleId}")
  public ProductAvailabilityResponse getSchedule(
      @PathVariable UUID productId,
      @PathVariable UUID scheduleId
  ) {
    return ProductAvailabilityResponse.from(
        productSearchService.getProductSchedule(productId, scheduleId)
    );
  }
}