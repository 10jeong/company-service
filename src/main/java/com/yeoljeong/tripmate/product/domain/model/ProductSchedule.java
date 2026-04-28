package com.yeoljeong.tripmate.product.domain.model;

import com.yeoljeong.tripmate.product.domain.enums.ScheduleStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "p_product_schedule",
    //product_id + date 조합이 유일 해야 한다
    uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "date"})}
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductSchedule {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "product_id", nullable = false)
  private UUID productId;

  @Column(nullable = false)
  private LocalDate date;

  @Column(nullable = false)
  private int stock;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ScheduleStatus status;

  @Builder(access = AccessLevel.PRIVATE)
  private ProductSchedule(
      UUID productId,
      LocalDate date,
      int stock
  ) {
    this.productId = productId;
    this.date = validateDate(date);
    this.stock = validateStock(stock);
    this.status = ScheduleStatus.ACTIVE;
  }

  public static ProductSchedule create(
      UUID productId,
      LocalDate date,
      int stock
  ) {
    return ProductSchedule.builder()
        .productId(productId)
        .date(date)
        .stock(stock)
        .build();
  }

  // 날짜 유효성 검증 (필수 값 체크)
  private static LocalDate validateDate(LocalDate date) {
    if (date == null) {
      throw new IllegalArgumentException("날짜는 필수입니다.");
    }
    return date;
  }

  // 재고 유효성 검증 (1 이상)
  private static int validateStock(int stock) {
    if (stock <= 0) {
      throw new IllegalArgumentException("재고는 1 이상이어야 합니다.");
    }
    return stock;
  }


  // 재고 차감 (수량 검증 포함)
  public void decreaseStock(int quantity) {
    if (this.stock < quantity) {
      throw new IllegalArgumentException("재고가 부족합니다.");
    }
    this.stock -= quantity;
  }

  // 일정 상태를 CLOSED로 변경 (판매 종료)
  public void close() {
    this.status = ScheduleStatus.CLOSED;
  }
}