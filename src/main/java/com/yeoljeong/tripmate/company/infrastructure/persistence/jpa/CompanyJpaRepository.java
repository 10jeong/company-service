package com.yeoljeong.tripmate.company.infrastructure.persistence.jpa;

import com.yeoljeong.tripmate.company.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyJpaRepository extends JpaRepository<Company, UUID> {

  //사업자 번호 중복 여부 확인
  boolean existsByBusinessNumber(String businessNumber);
}