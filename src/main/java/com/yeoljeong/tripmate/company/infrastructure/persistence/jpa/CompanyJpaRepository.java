package com.yeoljeong.tripmate.company.infrastructure.persistence.jpa;

import com.yeoljeong.tripmate.company.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyJpaRepository extends JpaRepository<Company, UUID> {

  boolean existsByBusinessNumber(String businessNumber);
}