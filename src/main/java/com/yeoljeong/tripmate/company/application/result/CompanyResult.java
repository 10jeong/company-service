package com.yeoljeong.tripmate.company.application.result;

import com.yeoljeong.tripmate.company.domain.entity.Company;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

/**
 * Service 처리 결과를 담는 Result DTO
 * (Service → Controller)
 */

@Getter
@Builder
public class CompanyResult {

  private UUID id;
  private String name;
  private String businessNumber;
  private String description;
  private String email;
  private String phone;
  private String status;
  private LocalDateTime createdAt;

  // Entity → Result 변환
  public static CompanyResult from(Company company) {
    return CompanyResult.builder()
        .id(company.getId())
        .name(company.getName())
        .businessNumber(company.getBusinessNumber())
        .description(company.getDescription())
        .email(company.getContactEmail())
        .phone(company.getContactPhone())
        .status(company.getStatus().name())
        .createdAt(company.getCreatedAt())
        .build();
  }
}