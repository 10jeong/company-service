package com.yeoljeong.tripmate.company.domain.repository;

import com.yeoljeong.tripmate.company.domain.model.Company;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CompanyRepository {

  // 업체 저장
  Company save(Company company);

  //업체 목록 조회
  Slice<Company> findAll(Pageable pageable);

  // 업체 단건 조회
  Optional<Company> findById(UUID id);

  // 사업자 번호 중복 여부 확인
  // - 업체 생성 시 중복 검증 용도
  boolean existsByBusinessNumber(String businessNumber);
}