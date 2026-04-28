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

  @Override
  public Product save(Product product) {
    return productJpaRepository.save(product);
  }

  @Override
  public Optional<Product> findById(UUID id) {
    return productJpaRepository.findById(id);
  }

  @Override
  public Slice<Product> findAll(Pageable pageable) {
    return productJpaRepository.findAllBy(pageable);
  }
}
