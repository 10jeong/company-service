package com.yeoljeong.tripmate.company.presentation.dto.response;

import com.yeoljeong.tripmate.company.domain.entity.Company;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public class CompanyResponse {

  private UUID id;
  private String name;
  private String businessNumber;
  private String description;
  private String email;
  private String phone;
  private String status;
  private LocalDateTime createdAt;

  public static CompanyResponse from(Company company) {
    return CompanyResponse.builder()
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
