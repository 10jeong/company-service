package com.yeoljeong.tripmate.company.presentation.dto.request;

import com.yeoljeong.tripmate.company.application.command.CreateCompanyCommand;
import lombok.Getter;

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
