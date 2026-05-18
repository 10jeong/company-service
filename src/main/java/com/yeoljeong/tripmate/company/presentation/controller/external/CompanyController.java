package com.yeoljeong.tripmate.company.presentation.controller.external;

import com.yeoljeong.tripmate.auth.annotation.LoginUser;
import com.yeoljeong.tripmate.auth.annotation.RequireRole;
import com.yeoljeong.tripmate.auth.context.UserContext;
import com.yeoljeong.tripmate.company.application.dto.result.CompanyResult;
import com.yeoljeong.tripmate.company.application.service.command.CompanyCommandService;
import com.yeoljeong.tripmate.company.application.service.query.CompanyQueryService;
import com.yeoljeong.tripmate.company.presentation.dto.request.CompanyRequest;
import com.yeoljeong.tripmate.company.presentation.dto.response.CompanyResponse;
import com.yeoljeong.tripmate.response.ApiResponse;
import com.yeoljeong.tripmate.response.constants.CommonSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {

  private final CompanyCommandService commandService;
  private final CompanyQueryService queryService;

  // 생성
  @RequireRole("SELLER")
  @PostMapping
  public ApiResponse<CompanyResponse> createCompany(
      @Valid @RequestBody CompanyRequest request
  ) {
    CompanyResult result = commandService.createCompany(request.toCommand());
    CompanyResponse response = CompanyResponse.from(result);

    return ApiResponse.success(CommonSuccessCode.CREATE, response);
  }

  //목록 조회
  @GetMapping
  public ApiResponse<Slice<CompanyResponse>> getCompanies(
      @PageableDefault(size = 10) Pageable pageable
  ) {
    return ApiResponse.success(CommonSuccessCode.OK,
        queryService.getCompanies(pageable).map(CompanyResponse::from));
  }

  // 단건 조회
  @GetMapping("/{companyId}")
  public ApiResponse<CompanyResponse> getCompany(@PathVariable UUID companyId) {

    CompanyResult result = queryService.getCompany(companyId);
    CompanyResponse response = CompanyResponse.from(result);

    return ApiResponse.success(CommonSuccessCode.OK, response);
  }

  @RequireRole("SELLER")
  @GetMapping("/me")
  public ApiResponse<Slice<CompanyResponse>> getMyCompanies(
      @LoginUser UserContext user,
      @PageableDefault(size = 10) Pageable pageable
  ) {
    return ApiResponse.success(CommonSuccessCode.OK,
        queryService.getMyCompanies(user.userId(), pageable).map(CompanyResponse::from));
  }
}