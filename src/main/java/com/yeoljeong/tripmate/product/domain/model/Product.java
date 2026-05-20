package com.yeoljeong.tripmate.product.domain.model;

import com.yeoljeong.tripmate.domain.BaseAuditEntity;
import com.yeoljeong.tripmate.exception.BusinessException;
import com.yeoljeong.tripmate.product.domain.enums.ProductStatus;
import com.yeoljeong.tripmate.product.domain.exception.ProductErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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

  @Embedded
  private ProductAddress address;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal price;

  @Column
  private String imageUrl;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProductStatus status;

  @Builder(access = AccessLevel.PRIVATE)
  private Product(
      UUID companyId,
      String productName,
      String description,
      ProductAddress address,
      BigDecimal price,
      String imageUrl
  ) {
    this.companyId = companyId;
    this.productName = productName;
    this.description = description;
    this.address = validateAddress(address);
    this.price = validatePrice(price);
    this.imageUrl = validateImageUrl(imageUrl);
    this.status = ProductStatus.ACTIVE;
  }

  public static Product create(
      UUID companyId,
      String productName,
      String description,
      ProductAddress address,
      BigDecimal price,
      String imageUrl
  ) {
    return Product.builder()
        .companyId(companyId)
        .productName(productName)
        .description(description)
        .address(address)
        .price(price)
        .imageUrl(imageUrl)
        .build();
  }

  // 주소 검증 메서드
  private static ProductAddress validateAddress(ProductAddress address) {
    if (address == null) {
      throw new BusinessException(ProductErrorCode.INVALID_PRODUCT_ADDRESS);
    }
    return address;
  }

  // 가격 검증 메서드
  private static BigDecimal validatePrice(BigDecimal price) {
    if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
      throw new BusinessException(ProductErrorCode.INVALID_PRODUCT_PRICE);
    }
    return price;
  }

  // 이미지 URL 검증 메서드 추가
  private static String validateImageUrl(String imageUrl) {
    if (imageUrl == null || imageUrl.isBlank()) {
      throw new BusinessException(ProductErrorCode.INVALID_PRODUCT_IMAGE_URL);
    }
    return imageUrl;
  }
}