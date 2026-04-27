package com.yeoljeong.tripmate.company.presentation.dto.response;

import com.yeoljeong.tripmate.company.application.result.CompanyResult;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 클라이언트에 응답하기 위한 Response DTO
 * (Controller → Client)
 */
public record CompanyResponse(

    UUID id,
    String name,
    String businessNumber,
    String description,
    String email,
    String phone,
    String status,
    LocalDateTime createdAt

) {
  public static CompanyResponse from(CompanyResult result) {
    return new CompanyResponse(
        result.getId(),
        result.getName(),
        result.getBusinessNumber(),
        result.getDescription(),
        result.getEmail(),
        result.getPhone(),
        result.getStatus(),
        result.getCreatedAt()
    );
  }
}