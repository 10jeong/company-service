package com.yeoljeong.tripmate.product.infrastructure.persistence.jpa;

import com.yeoljeong.tripmate.product.domain.entity.Product;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<Product, UUID> {
  Slice<Product> findAllBy(Pageable pageable);
}
