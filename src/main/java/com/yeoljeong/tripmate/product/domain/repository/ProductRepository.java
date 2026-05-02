package com.yeoljeong.tripmate.product.domain.repository;

import com.yeoljeong.tripmate.product.domain.model.Product;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ProductRepository {

  // 상품 저장
  Product save(Product product);

  // 상품 단건 조회
  Optional<Product> findById(UUID id);

  // 상품 목록 조회
  Slice<Product> findAll(Pageable pageable);
}
