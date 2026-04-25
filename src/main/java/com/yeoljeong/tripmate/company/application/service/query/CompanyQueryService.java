package com.yeoljeong.tripmate.company.application.service.query;

import com.yeoljeong.tripmate.company.application.result.CompanyResult;
import com.yeoljeong.tripmate.company.domain.entity.Company;
import com.yeoljeong.tripmate.company.domain.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyQueryService {

  private final CompanyRepository companyRepository;

  public CompanyResult getCompany(UUID companyId) {
    Company company = findCompany(companyId);
    return CompanyResult.from(company);
  }

  //==메서드==

  // 업체 단건 조회
  private Company findCompany(UUID companyId) {
    return companyRepository.findById(companyId)
        .orElseThrow(() -> new IllegalArgumentException("회사를 찾을 수 없습니다."));
  }
}
