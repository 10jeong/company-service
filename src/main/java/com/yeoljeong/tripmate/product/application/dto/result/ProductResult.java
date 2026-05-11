package com.yeoljeong.tripmate.product.application.dto.result;

import com.yeoljeong.tripmate.product.domain.model.Product;
import com.yeoljeong.tripmate.product.domain.model.ProductAddress;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Service 처리 결과를 담는 Result DTO (Service → Controller)
 */

//값만 담고 내보내는 용도라서 불변인 record 로 구현

public record ProductResult(
    UUID id,
    UUID companyId,
    String productName,
    String description,
    ProductAddress address,
    BigDecimal price,
    String status
) {

  public static ProductResult from(Product product) {
    return new ProductResult(
        product.getId(),
        product.getCompanyId(),
        product.getProductName(),
        product.getDescription(),
        product.getAddress(),
        product.getPrice(),
        product.getStatus().name()
    );
  }
}