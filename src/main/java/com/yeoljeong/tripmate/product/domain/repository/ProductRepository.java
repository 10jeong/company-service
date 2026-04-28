package com.yeoljeong.tripmate.product.domain.repository;

import com.yeoljeong.tripmate.product.domain.model.Product;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ProductRepository {

  Product save(Product product);

  Optional<Product> findById(UUID id);

  Slice<Product> findAll(Pageable pageable);
}
