package com.yeoljeong.tripmate.company.presentation.dto.request;

import com.yeoljeong.tripmate.company.application.command.CreateCompanyCommand;
import lombok.Getter;

/**
 * Company 생성 요청을 받는 Request DTO
 * (클라이언트 → Controller)
 */
@Getter
public class CreateCompanyRequest {

  private String name;
  private String businessNumber;
  private String description;
  private String email;
  private String phone;

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
