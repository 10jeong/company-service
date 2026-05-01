package com.yeoljeong.tripmate.product.presentation.dto.response;

import com.yeoljeong.tripmate.product.application.dto.result.ProductResult;
import java.math.BigDecimal;
import java.util.UUID;

public record ProductPlanResponse(
    UUID productId,
    String productName,
    String country,
    String state,
    String city,
    BigDecimal price
) {


  public static ProductPlanResponse from(ProductResult result) {
    return new ProductPlanResponse(
        result.id(),
        result.productName(),
        result.address().getCountry().name(),
        result.address().getState(),
        result.address().getCity(),
        result.price()
    );

  }
}
