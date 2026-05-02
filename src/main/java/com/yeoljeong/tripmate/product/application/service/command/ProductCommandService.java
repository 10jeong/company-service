package com.yeoljeong.tripmate.product.application.service.command;

import com.yeoljeong.tripmate.company.presentation.dto.response.CompanyResponse;
import com.yeoljeong.tripmate.exception.BusinessException;
import com.yeoljeong.tripmate.product.application.dto.command.CreateProductCommand;
import com.yeoljeong.tripmate.product.application.dto.result.ProductResult;
import com.yeoljeong.tripmate.product.application.service.client.CompanyClient;
import com.yeoljeong.tripmate.product.domain.exception.ProductErrorCode;
import com.yeoljeong.tripmate.product.domain.model.Product;
import com.yeoljeong.tripmate.product.domain.repository.ProductRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCommandService {

  private final ProductRepository productRepository;
  private final CompanyClient companyClient;

  //상품 생성
  /**
   * - 업체 조회(FeignClient 내부 통신)
   * - 업체 권한 및 상태 검증
   * - 상품 엔티티 생성 및 저장
   */
  public ProductResult createProduct(
      CreateProductCommand command,
      UUID createdBy
  ) {

    CompanyResponse company =
        companyClient.getCompany(command.getCompanyId());

    validateCompany(company, createdBy);

    Product product = command.toEntity();

    Product saved = productRepository.save(product);

    return ProductResult.from(saved);
  }

  //업체 검증 메서드
  private void validateCompany(
      CompanyResponse company,
      UUID createdBy
  ) {

    // 현재 로그인한 사용자가 업체 생성자인지 검증(본인 업체만 상품 등록 가능)
    if (!company.createdBy().equals(createdBy)) {
      throw new BusinessException(ProductErrorCode.UNAUTHORIZED_COMPANY_ACCESS);
    }

    //업체 ACTIVE 상태인 업체만 상품 등록 가능
    if (!company.isActive()) {
      throw new BusinessException(ProductErrorCode.INVALID_COMPANY_STATUS);
    }
  }
}
