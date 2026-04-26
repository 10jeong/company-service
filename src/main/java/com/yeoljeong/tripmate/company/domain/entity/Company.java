package com.yeoljeong.tripmate.company.domain.entity;

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

  @Builder(access = AccessLevel.PRIVATE)
  private Company(
      String name,
      String businessNumber,
      String description,
      String contactEmail,
      String contactPhone
  ) {
    this.name = name;
    this.businessNumber = businessNumber;
    this.description = description;
    this.contactEmail = contactEmail;
    this.contactPhone = contactPhone;
    this.status = CompanyStatus.ACTIVE;
  }

  public static Company create(
      String name,
      String businessNumber,
      String description,
      String email,
      String phone
  ) {
    return Company.builder()
        .name(name)
        .businessNumber(businessNumber)
        .description(description)
        .contactEmail(email)
        .contactPhone(phone)
        .build();
  }
}