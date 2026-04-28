package com.yeoljeong.tripmate.product.presentation.dto.request;

import com.yeoljeong.tripmate.product.application.dto.command.CreateProductCommand;
import com.yeoljeong.tripmate.product.domain.enums.Country;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(

    @NotBlank(message = "상품명은 필수입니다.")
    @Size(max = 100, message = "상품명은 100자 이내여야 합니다.")
    String productName,

    @NotBlank(message = "상품 설명은 필수입니다.")
    @Size(max = 1000, message = "상품 설명은 1000자 이내여야 합니다.")
    String description,

    @NotNull(message = "국가는 필수입니다.")
    @Pattern(regexp = "^(KR|JP)$", message = "유효하지 않은 국가 코드입니다.")
    String country,

    @NotBlank(message = "도/주는 필수입니다.")
    @Size(max = 100, message = "도/주는 100자 이내여야 합니다.")
    String state,

    @NotBlank(message = "도시는 필수입니다.")
    @Size(max = 100, message = "도시는 100자 이내여야 합니다.")
    String city,

    @NotBlank(message = "상세 주소는 필수입니다.")
    @Size(max = 255, message = "주소는 255자 이내여야 합니다.")
    String addressLine,

    @NotNull(message = "가격은 필수입니다.")
    @Digits(integer = 8, fraction = 2, message = "가격은 정수 8자리, 소수 2자리 이내여야 합니다.")
    @Positive(message = "가격은 0보다 커야 합니다.")
    BigDecimal price


) {

  public CreateProductCommand toCommand(UUID companyId) {
    return CreateProductCommand.builder()
        .companyId(companyId)
        .productName(productName)
        .description(description)
        .country(Country.valueOf(country))
        .state(state)
        .city(city)
        .addressLine(addressLine)
        .price(price)
        .build();
  }
}