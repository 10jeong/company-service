package com.yeoljeong.tripmate.company.infrastructure.persistence.repositoryImpl;

import com.yeoljeong.tripmate.company.domain.entity.Company;
import com.yeoljeong.tripmate.company.domain.repository.CompanyRepository;
import com.yeoljeong.tripmate.company.infrastructure.persistence.jpa.CompanyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepository {

  private final CompanyJpaRepository jpaRepository;

  @Override
  public Company save(Company company) {
    return jpaRepository.save(company);
  }

  @Override
  public Optional<Company> findById(UUID id) {
    return jpaRepository.findById(id);
  }

  @Override
  public boolean existsByBusinessNumber(String businessNumber) {
    return jpaRepository.existsByBusinessNumber(businessNumber);
  }
}