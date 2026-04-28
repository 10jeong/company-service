package com.yeoljeong.tripmate.company.domain.repository;

import com.yeoljeong.tripmate.company.domain.model.Company;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository {

  Company save(Company company);

  Optional<Company> findById(UUID id);

  boolean existsByBusinessNumber(String businessNumber);
}