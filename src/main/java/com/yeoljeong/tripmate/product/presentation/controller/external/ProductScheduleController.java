package com.yeoljeong.tripmate.product.presentation.controller.external;

import com.yeoljeong.tripmate.auth.annotation.LoginUser;
import com.yeoljeong.tripmate.auth.annotation.RequireRole;
import com.yeoljeong.tripmate.auth.context.UserContext;
import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleCommandResult;

import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleQueryResult;
import com.yeoljeong.tripmate.product.application.service.command.ProductScheduleCommandService;
import com.yeoljeong.tripmate.product.application.service.query.ProductScheduleQueryService;
import com.yeoljeong.tripmate.product.presentation.dto.request.ProductScheduleRequest;
import com.yeoljeong.tripmate.product.presentation.dto.response.ProductScheduleQueryResponse;
import com.yeoljeong.tripmate.product.presentation.dto.response.ProductScheduleResponse;
import com.yeoljeong.tripmate.response.ApiResponse;
import com.yeoljeong.tripmate.response.constants.CommonSuccessCode;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductScheduleController {

  private final ProductScheduleCommandService scheduleCommandService;
  private final ProductScheduleQueryService scheduleQueryService;

  // 상품 스케줄 일괄 생성
  @RequireRole("SELLER")
  @PostMapping("/{productId}/schedules/bulk")
  ResponseEntity<ApiResponse<ProductScheduleResponse>> createSchedules(
      @PathVariable UUID productId,
      @RequestBody ProductScheduleRequest request,
      @LoginUser UserContext user
  ) {

    ProductScheduleCommandResult result =
        scheduleCommandService.createSchedules(
            request.toCommand(productId),
            user.userId()
        );

    ProductScheduleResponse response = ProductScheduleResponse.from(result);

    return ResponseEntity.ok(
        ApiResponse.success(CommonSuccessCode.OK, response)
    );
  }

  // 스케줄 단건 조회
  @GetMapping("/{productId}/schedules/{scheduleId}")
  public ApiResponse<ProductScheduleQueryResponse> getSchedule(
      @PathVariable UUID productId,
      @PathVariable UUID scheduleId
  ) {
    ProductScheduleQueryResult result =
        scheduleQueryService.getSchedule(productId, scheduleId);

    return ApiResponse.success(
        CommonSuccessCode.OK,
        ProductScheduleQueryResponse.from(result)
    );
  }

  // 스케줄 목록 조회
  @GetMapping("/{productId}/schedules")
  public ApiResponse<Slice<ProductScheduleQueryResponse>> getSchedules(
      @PathVariable UUID productId,
      @PageableDefault(size = 10) Pageable pageable
  ) {
    Slice<ProductScheduleQueryResponse> response =
        scheduleQueryService.getSchedules(productId, pageable)
            .map(ProductScheduleQueryResponse::from);

    return ApiResponse.success(CommonSuccessCode.OK, response);
  }
}