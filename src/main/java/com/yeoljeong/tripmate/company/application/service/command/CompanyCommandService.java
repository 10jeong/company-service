package com.yeoljeong.tripmate.company.application.service.command;

import com.yeoljeong.tripmate.company.application.command.CreateCompanyCommand;
import com.yeoljeong.tripmate.company.application.result.CompanyResult;
import com.yeoljeong.tripmate.company.domain.entity.Company;
import com.yeoljeong.tripmate.company.domain.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyCommandService {

  private final CompanyRepository companyRepository;

  //업체 생성
  public CompanyResult createCompany(CreateCompanyCommand command) {

    validateDuplicateCompany(command.getBusinessNumber());

    Company company = command.toEntity();

    Company saved = companyRepository.save(company);

    return CompanyResult.from(saved);
  }

  //==메서드==

  //사업자등록번호 중복 검증
  private void validateDuplicateCompany(String businessNumber) {
    if (companyRepository.existsByBusinessNumber(businessNumber)) {
      throw new IllegalArgumentException("이미 등록된 사업자입니다.");
    }
  }
}