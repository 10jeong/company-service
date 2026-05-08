package com.yeoljeong.tripmate.product.infrastructure.external;

import com.yeoljeong.tripmate.company.presentation.dto.response.CompanyResponse;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// ngrok 테스트시 url ="https://resupply-botch-styling.ngrok-free.dev" 붙이기
//실제 HTTP 호출
@FeignClient(name = "company-service" , url ="https://resupply-botch-styling.ngrok-free.dev" )
public interface CompanyFeignClient {

  @GetMapping("/internal/companies/{companyId}")
  CompanyResponse getCompany(@PathVariable UUID companyId); // ← 래퍼 제거
}
