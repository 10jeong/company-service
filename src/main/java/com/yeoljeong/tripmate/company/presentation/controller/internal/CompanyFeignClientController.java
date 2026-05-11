package com.yeoljeong.tripmate.company.presentation.controller.internal;


import com.yeoljeong.tripmate.company.application.dto.result.CompanyResult;
import com.yeoljeong.tripmate.company.application.service.query.CompanyQueryService;
import com.yeoljeong.tripmate.company.presentation.dto.response.CompanyResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/companies")
public class CompanyFeignClientController {

  private final CompanyQueryService queryService;

  @GetMapping("/{companyId}")
  public CompanyResponse getCompany(
      @PathVariable UUID companyId
  ) {
    CompanyResult result = queryService.getCompany(companyId);
    return CompanyResponse.from(result);
  }
}
