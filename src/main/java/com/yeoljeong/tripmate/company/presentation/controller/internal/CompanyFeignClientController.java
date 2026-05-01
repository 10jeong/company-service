package com.yeoljeong.tripmate.company.presentation.controller.internal;


import com.yeoljeong.tripmate.company.application.dto.result.CompanyResult;
import com.yeoljeong.tripmate.company.application.service.query.CompanyQueryService;
import com.yeoljeong.tripmate.company.presentation.dto.response.CompanyResponse;
import com.yeoljeong.tripmate.response.ApiResponse;
import com.yeoljeong.tripmate.response.constants.CommonSuccessCode;
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
  public ApiResponse<CompanyResponse> getCompany(
      @PathVariable UUID companyId
  ) {

    CompanyResult result = queryService.getCompany(companyId);
    CompanyResponse response = CompanyResponse.from(result);

    return ApiResponse.success(CommonSuccessCode.OK, response);
  }
}
