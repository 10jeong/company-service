package com.yeoljeong.tripmate.company.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCompanyCommand {

  private String name;
  private String businessNumber;
  private String description;
  private String email;
  private String phone;
}
