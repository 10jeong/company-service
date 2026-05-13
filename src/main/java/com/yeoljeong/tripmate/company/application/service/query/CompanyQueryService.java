package com.yeoljeong.tripmate.company.application.service.query;

import com.yeoljeong.tripmate.company.application.dto.result.CompanyResult;
import com.yeoljeong.tripmate.company.domain.model.Company;
import com.yeoljeong.tripmate.company.domain.exception.CompanyErrorCode;
import com.yeoljeong.tripmate.company.domain.repository.CompanyRepository;

import com.yeoljeong.tripmate.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyQueryService {

  private final CompanyRepository companyRepository;

  // 업체 단건 조회
  public CompanyResult getCompany(UUID companyId) {
    Company company = findCompany(companyId);
    return CompanyResult.from(company);
  }

  //업체 목록 조회
  public Slice<CompanyResult> getCompanies(Pageable pageable) {
    return companyRepository.findAll(pageable)
        .map(CompanyResult::from);
  }

  //==메서드==

  // 업체 단건 조회 - ID로 조회, 없으면 예외 발생
  private Company findCompany(UUID companyId) {
    return companyRepository.findById(companyId)
        .orElseThrow(() -> new BusinessException(CompanyErrorCode.COMPANY_NOT_FOUND));
  }
}
