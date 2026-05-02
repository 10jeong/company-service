package com.yeoljeong.tripmate.product.infrastructure.external;

import com.yeoljeong.tripmate.company.presentation.dto.response.CompanyResponse;
import com.yeoljeong.tripmate.product.infrastructure.external.dto.CompanyFeignResponse;
import com.yeoljeong.tripmate.response.ApiResponse;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//실제 HTTP 호출
@FeignClient(name = "company-service")
public interface CompanyFeignClient {

  @GetMapping("/internal/companies/{companyId}")
  CompanyFeignResponse<CompanyResponse> getCompany(
      @PathVariable UUID companyId
  );
}
