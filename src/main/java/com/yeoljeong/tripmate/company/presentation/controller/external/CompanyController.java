package com.yeoljeong.tripmate.company.presentation.controller.external;

import com.yeoljeong.tripmate.company.application.result.CompanyResult;
import com.yeoljeong.tripmate.company.application.service.command.CompanyCommandService;
import com.yeoljeong.tripmate.company.application.service.query.CompanyQueryService;

import com.yeoljeong.tripmate.company.presentation.dto.request.CreateCompanyRequest;
import com.yeoljeong.tripmate.company.presentation.dto.response.CompanyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {

  private final CompanyCommandService commandService;
  private final CompanyQueryService queryService;

  // 생성
  @PostMapping
  public CompanyResponse createCompany(@Valid @RequestBody CreateCompanyRequest request) {

    CompanyResult result = commandService.createCompany(request.toCommand());

    return CompanyResponse.from(result);
  }


  // 단건 조회
  @GetMapping("/{companyId}")
  public CompanyResponse getCompany(@PathVariable UUID companyId) {

    CompanyResult result = queryService.getCompany(companyId);

    return CompanyResponse.from(result);
  }
}
