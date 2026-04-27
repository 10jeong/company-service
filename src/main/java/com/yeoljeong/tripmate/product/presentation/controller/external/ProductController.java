package com.yeoljeong.tripmate.product.presentation.controller.external;

import com.yeoljeong.tripmate.product.application.command.CreateProductCommand;
import com.yeoljeong.tripmate.product.application.result.ProductResult;
import com.yeoljeong.tripmate.product.application.service.command.ProductCommandService;
import com.yeoljeong.tripmate.product.presentation.dto.request.ProductRequest;
import com.yeoljeong.tripmate.product.presentation.dto.response.ProductResponse;
import com.yeoljeong.tripmate.response.ApiResponse;
import com.yeoljeong.tripmate.response.constants.CommonSuccessCode;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductCommandService productCommandService;

  // 상품 생성
  //1. 지금 → @RequestHeader로 임시 처리
  //2. 나중에 Gateway 구현되면 → 자동으로 헤더에 들어옴
  //3.1 토큰 구현되면 → @AuthenticationPrincipal로 변경
  //3.2 나중에 Gateway + Common Module 구현되면 → @CurrentUser UserContext로 변경 ?
  @PostMapping
  public ApiResponse<ProductResponse> createProduct(
      @RequestBody @Valid ProductRequest request,
      @RequestHeader("X-User-Id") UUID userId,
      @RequestHeader("X-Company-Id") UUID companyId
  ) {
    CreateProductCommand command = request.toCommand(companyId);
    ProductResult result = productCommandService.createProduct(command);
    ProductResponse response = ProductResponse.from(result);
    return ApiResponse.success(CommonSuccessCode.CREATE, response);
  }
}