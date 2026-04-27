package com.yeoljeong.tripmate.product.infrastructure.persistence.repositoryImpl;

import com.yeoljeong.tripmate.product.domain.entity.Product;
import com.yeoljeong.tripmate.product.domain.repository.ProductRepository;
import com.yeoljeong.tripmate.product.infrastructure.persistence.jpa.JpaProductRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

  private final JpaProductRepository jpaProductRepository;

  @Override
  public Product save(Product product) {
    return jpaProductRepository.save(product);
  }

  @Override
  public Optional<Product> findById(UUID id) {
    return jpaProductRepository.findById(id);
  }

  @Override
  public Slice<Product> findAll(Pageable pageable) {
    return jpaProductRepository.findAllBy(pageable);
  }
}
