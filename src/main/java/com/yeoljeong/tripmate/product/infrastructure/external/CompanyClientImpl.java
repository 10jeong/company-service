package com.yeoljeong.tripmate.product.infrastructure.external;

import com.yeoljeong.tripmate.company.presentation.dto.response.CompanyResponse;
import com.yeoljeong.tripmate.product.application.service.client.CompanyClient;
import com.yeoljeong.tripmate.product.infrastructure.external.dto.CompanyClientResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyClientImpl implements CompanyClient {

  private final CompanyFeignClient companyFeignClient;

  @Override
  public CompanyClientResponse getCompany(UUID companyId) {
    CompanyResponse response = companyFeignClient.getCompany(companyId);
    return new CompanyClientResponse(
        response.createdBy(),
        response.isActive()
    );
  }
}