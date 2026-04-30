package com.yeoljeong.tripmate.product.presentation.controller.external;

import com.yeoljeong.tripmate.product.application.service.query.ProductSearchService;
import com.yeoljeong.tripmate.product.presentation.dto.response.ProductScheduleInfoResponse;
import com.yeoljeong.tripmate.response.ApiResponse;
import com.yeoljeong.tripmate.response.constants.CommonSuccessCode;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductAvailabilityController {

  private final ProductSearchService searchService;

  // 날짜 기준 예약 가능한 상품 조회
  @GetMapping("/available")
  public ApiResponse<Slice<ProductScheduleInfoResponse>> getAvailableProducts(
      @RequestParam LocalDate date,
      @PageableDefault(size = 10) Pageable pageable
  ) {
    Slice<ProductScheduleInfoResponse> response =
        searchService.getAvailableProducts(date, pageable)
            .map(ProductScheduleInfoResponse::from);

    return ApiResponse.success(CommonSuccessCode.OK, response);
  }
}