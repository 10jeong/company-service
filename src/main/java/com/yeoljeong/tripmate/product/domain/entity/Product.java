package com.yeoljeong.tripmate.product.domain.entity;

import com.yeoljeong.tripmate.domain.BaseAuditEntity;
import com.yeoljeong.tripmate.product.domain.enums.Country;
import com.yeoljeong.tripmate.product.domain.enums.ProductStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseAuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private UUID companyId;

  @Column(nullable = false)
  private String productName;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Country country;

  @Column(nullable = false)
  private String state;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String addressLine;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal price;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProductStatus status;

  @Builder(access = AccessLevel.PRIVATE)
  private Product(
      UUID companyId,
      String productName,
      String description,
      Country country,
      String state,
      String city,
      String addressLine,
      BigDecimal price
  ) {
    this.companyId = companyId;
    this.productName = productName;
    this.description = description;
    this.country = country;
    this.state = state;
    this.city = city;
    this.addressLine = addressLine;
    this.price = price;
    this.status = ProductStatus.ACTIVE;
  }

  public static Product create(
      UUID companyId,
      String productName,
      String description,
      Country country,
      String state,
      String city,
      String addressLine,
      BigDecimal price
  ) {
    return Product.builder()
        .companyId(companyId)
        .productName(productName)
        .description(description)
        .country(country)
        .state(state)
        .city(city)
        .addressLine(addressLine)
        .price(price)
        .build();
  }
}




