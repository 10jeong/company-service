package com.yeoljeong.tripmate.company.application.command;

import com.yeoljeong.tripmate.company.domain.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Company 생성용 Command DTO
 * (Controller → Service)
 */

@Getter
@AllArgsConstructor
public class CreateCompanyCommand {

  private String name;
  private String businessNumber;
  private String description;
  private String email;
  private String phone;

  public Company toEntity() {
    return Company.create(
        name,
        businessNumber,
        description,
        email,
        phone
    );
  }
}
