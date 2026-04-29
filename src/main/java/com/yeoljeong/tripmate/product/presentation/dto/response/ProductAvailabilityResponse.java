package com.yeoljeong.tripmate.product.presentation.dto.response;

import com.yeoljeong.tripmate.product.application.dto.result.ProductAvailabilityResult;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ProductAvailabilityResponse(
    UUID productId,
    String productName,
    String country,
    String state,
    String city,
    String addressLine,
    BigDecimal price,
    LocalDate date,
    int stock,
    String status
) {
  public static ProductAvailabilityResponse from(ProductAvailabilityResult result) {
    return new ProductAvailabilityResponse(
        result.productId(),
        result.productName(),
        result.country(),
        result.state(),
        result.city(),
        result.addressLine(),
        result.price(),
        result.date(),
        result.stock(),
        result.status()
    );
  }
}
