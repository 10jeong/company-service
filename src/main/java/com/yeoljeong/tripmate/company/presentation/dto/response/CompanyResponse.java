package com.yeoljeong.tripmate.company.presentation.dto.response;

import com.yeoljeong.tripmate.company.application.result.CompanyResult;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

/**
 * 클라이언트에 응답하기 위한 Response DTO
 * (Controller → Client)
 */

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

  public static CompanyResponse from(CompanyResult result) {
    return CompanyResponse.builder()
        .id(result.getId())
        .name(result.getName())
        .businessNumber(result.getBusinessNumber())
        .description(result.getDescription())
        .email(result.getEmail())
        .phone(result.getPhone())
        .status(result.getStatus())
        .build();
  }
}
