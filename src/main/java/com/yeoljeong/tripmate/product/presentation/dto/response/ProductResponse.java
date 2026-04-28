package com.yeoljeong.tripmate.product.presentation.dto.response;

import com.yeoljeong.tripmate.product.application.dto.result.ProductResult;
import com.yeoljeong.tripmate.product.domain.entity.ProductAddress;
import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(

    UUID id,
    UUID companyId,
    String productName,
    String description,
    ProductAddress address,
    BigDecimal price,
    String status

) {

  public static ProductResponse from(ProductResult result) {
    return new ProductResponse(
        result.id(),
        result.companyId(),
        result.productName(),
        result.description(),
        result.address(),
        result.price(),
        result.status()
    );
  }
}