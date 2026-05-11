package com.yeoljeong.tripmate.company.presentation.dto.request;

import com.yeoljeong.tripmate.company.application.dto.command.CreateCompanyCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Company 생성 요청을 받는 Request DTO
 * (클라이언트 → Controller)
 */
public record CompanyRequest(

    @NotBlank(message = "회사명은 필수입니다.")
    String name,

    @NotBlank(message = "사업자 등록번호는 필수입니다.")
    String businessNumber,

    String description,

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    String email,

    @NotBlank(message = "전화번호는 필수입니다.")
    String phone

) {
  public CreateCompanyCommand toCommand() {
    return new CreateCompanyCommand(
        name,
        businessNumber,
        description,
        email,
        phone
    );
  }
}