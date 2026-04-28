package com.yeoljeong.tripmate.product.application.service.query;

import com.yeoljeong.tripmate.exception.BusinessException;
import com.yeoljeong.tripmate.product.application.dto.result.ProductResult;
import com.yeoljeong.tripmate.product.domain.entity.Product;
import com.yeoljeong.tripmate.product.domain.exception.ProductErrorCode;
import com.yeoljeong.tripmate.product.domain.repository.ProductRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductQueryService {

  private final ProductRepository productRepository;

  // 단건 조회
  public ProductResult getProduct(UUID productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new BusinessException(ProductErrorCode.PRODUCT_NOT_FOUND));

    return ProductResult.from(product);
  }

  // 목록 조회
  public Slice<ProductResult> getProducts(Pageable pageable) {
    return productRepository.findAll(pageable)
        .map(ProductResult::from);
  }
}
