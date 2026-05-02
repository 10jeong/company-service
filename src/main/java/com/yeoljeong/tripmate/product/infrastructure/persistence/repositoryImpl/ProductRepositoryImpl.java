package com.yeoljeong.tripmate.product.infrastructure.persistence.repositoryImpl;

import com.yeoljeong.tripmate.product.domain.model.Product;
import com.yeoljeong.tripmate.product.domain.repository.ProductRepository;
import com.yeoljeong.tripmate.product.infrastructure.persistence.jpa.ProductJpaRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

  private final ProductJpaRepository productJpaRepository;

  //상품 저장
  @Override
  public Product save(Product product) {
    return productJpaRepository.save(product);
  }

  // 상품 단건 조회
  @Override
  public Optional<Product> findById(UUID id) {
    return productJpaRepository.findById(id);
  }

  // 상품 목록 조회
  @Override
  public Slice<Product> findAll(Pageable pageable) {
    return productJpaRepository.findAllBy(pageable);
  }
}
