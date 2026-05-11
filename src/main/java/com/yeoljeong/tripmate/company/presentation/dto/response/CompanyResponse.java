package com.yeoljeong.tripmate.company.presentation.dto.response;

import com.yeoljeong.tripmate.company.application.dto.result.CompanyResult;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 클라이언트에 응답하기 위한 Response DTO
 * (Controller → Client)
 */
public record CompanyResponse(

    UUID id,
    UUID createdBy,
    String name,
    String businessNumber,
    String description,
    String email,
    String phone,
    String status,
    LocalDateTime createdAt

) {

  // 업체 활성 상태 여부 반환
  // 상품 등록 가능한 업체인지 검증할 때 사용
  public boolean isActive() {
    return "ACTIVE".equals(status);
  }

  public static CompanyResponse from(CompanyResult result) {
    return new CompanyResponse(
        result.getId(),
        result.getCreatedBy(),
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