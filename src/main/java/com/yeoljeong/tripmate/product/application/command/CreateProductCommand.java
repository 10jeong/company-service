package com.yeoljeong.tripmate.product.application.command;

import com.yeoljeong.tripmate.product.domain.entity.Product;
import com.yeoljeong.tripmate.product.domain.enums.Country;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

/**
 * Company 생성용 Command DTO
 * (Controller → Service)
 */

// 나중에 값 추가되거나 바뀔 수 있고,
// JWT에서 꺼낸 값 세팅하는 경우가 있어서 클래스로 정의한다고한다..

@Getter
@Builder
public class CreateProductCommand {

  private UUID companyId;
  private String productName;
  private String description;
  private Country country;
  private String state;
  private String city;
  private String addressLine;
  private BigDecimal price;

  public Product toEntity() {
    return Product.create(
        companyId,
        productName,
        description,
        country,
        state,
        city,
        addressLine,
        price
    );
  }
}