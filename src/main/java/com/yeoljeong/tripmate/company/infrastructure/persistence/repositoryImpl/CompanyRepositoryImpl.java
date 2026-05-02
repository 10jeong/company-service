package com.yeoljeong.tripmate.company.infrastructure.persistence.repositoryImpl;

import com.yeoljeong.tripmate.company.domain.model.Company;
import com.yeoljeong.tripmate.company.domain.repository.CompanyRepository;
import com.yeoljeong.tripmate.company.infrastructure.persistence.jpa.CompanyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepository {

  // Spring Data JPA Repository 위임 객체
  private final CompanyJpaRepository jpaRepository;

  // 업체 저장
  @Override
  public Company save(Company company) {
    return jpaRepository.save(company);
  }

  // 업체 단건 조회
  @Override
  public Optional<Company> findById(UUID id) {
    return jpaRepository.findById(id);
  }

  // 사업자 번호 중복 여부 확인
  // - 업체 생성 시 중복 검증 용도
  @Override
  public boolean existsByBusinessNumber(String businessNumber) {
    return jpaRepository.existsByBusinessNumber(businessNumber);
  }
}