package com.yeoljeong.tripmate.product.application.service.command;

import com.yeoljeong.tripmate.product.application.command.CreateProductCommand;
import com.yeoljeong.tripmate.product.application.result.ProductResult;
import com.yeoljeong.tripmate.product.domain.entity.Product;
import com.yeoljeong.tripmate.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCommandService {

  private final ProductRepository productRepository;

  //상품 생성
  public ProductResult createProduct(CreateProductCommand command) {

    Product product = command.toEntity();

    Product saved = productRepository.save(product);

    return ProductResult.from(saved);
  }
}
