package com.yeoljeong.tripmate.product.presentation.controller.external;

import com.yeoljeong.tripmate.product.application.dto.command.CreateProductScheduleCommand;
import com.yeoljeong.tripmate.product.application.dto.result.ProductScheduleResult;

import com.yeoljeong.tripmate.product.application.service.command.ProductScheduleCommandService;
import com.yeoljeong.tripmate.product.presentation.dto.request.ProductScheduleRequest;
import com.yeoljeong.tripmate.product.presentation.dto.response.ProductScheduleResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

  //상품 스케줄 일괄 생성
  @PostMapping("/{productId}/schedules/bulk")
  public ResponseEntity<ProductScheduleResponse> createSchedules(
      @PathVariable UUID productId,
      @RequestBody ProductScheduleRequest request
  ) {

    ProductScheduleResult result =
        scheduleCommandService.createSchedules(request.toCommand(productId));

    return ResponseEntity.ok(ProductScheduleResponse.from(result));
  }
  }

