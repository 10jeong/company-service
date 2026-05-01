package com.yeoljeong.tripmate.product.infrastructure.external;

import com.yeoljeong.tripmate.company.presentation.dto.response.CompanyResponse;
import com.yeoljeong.tripmate.product.application.service.client.CompanyClient;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyClientImpl implements CompanyClient {

  private final CompanyFeignClient companyFeignClient;

  @Override
  public CompanyResponse getCompany(UUID companyId) {

    return companyFeignClient
        .getCompany(companyId)
        .getData();
  }
}