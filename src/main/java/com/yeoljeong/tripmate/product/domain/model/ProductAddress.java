package com.yeoljeong.tripmate.product.domain.model;

import com.yeoljeong.tripmate.product.domain.enums.Country;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductAddress {

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Country country;

  @Column(nullable = false)
  private String state;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String addressLine;

  public ProductAddress(
      Country country,
      String state,
      String city,
      String addressLine
  ) {
    this.country = country;
    this.state = state;
    this.city = city;
    this.addressLine = addressLine;
  }
}
