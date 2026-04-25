package com.yeoljeong.tripmate.company.domain.entity;

import com.yeoljeong.tripmate.company.application.command.CreateCompanyCommand;
import com.yeoljeong.tripmate.company.domain.enums.CompanyStatus;
import com.yeoljeong.tripmate.domain.BaseAuditEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "p_company")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company extends BaseAuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(length = 100, nullable = false)
  private String name;

  @Column(name = "business_number", length = 20, nullable = false, unique = true)
  private String businessNumber;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String description;

  @Column(name = "contact_email", length = 100, nullable = false)
  private String contactEmail;

  @Column(name = "contact_phone", length = 20, nullable = false)
  private String contactPhone;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CompanyStatus status;

//생성 메서드
  public static Company create(CreateCompanyCommand command) {

    Company company = new Company();

    company.name = command.getName();
    company.businessNumber = command.getBusinessNumber();
    company.description = command.getDescription();
    company.contactEmail = command.getEmail();
    company.contactPhone = command.getPhone();
    company.status = CompanyStatus.ACTIVE;

    return company;
  }
}
