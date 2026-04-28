package com.yeoljeong.tripmate.product.domain.model;

import com.yeoljeong.tripmate.product.domain.enums.ScheduleStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(nullable = false)
  private LocalDate date;

  @Column(nullable = false)
  private int stock;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ScheduleStatus status;

  @Builder(access = AccessLevel.PRIVATE)
  private ProductSchedule(
      Product product,
      LocalDate date,
      int stock
  ) {
    this.product = validateProduct(product);
    this.date = validateDate(date);
    this.stock = validateStock(stock);
    this.status = ScheduleStatus.ACTIVE;
  }

  //상품이랑 동시에 생성 되는게 아니라 따로도 만드니까 public
  public static ProductSchedule create(
      Product product,
      LocalDate date,
      int stock
  ) {
    return ProductSchedule.builder()
        .product(product)
        .date(date)
        .stock(stock)
        .build();
  }

  // ProductSchedule은 반드시 상품에 속해야 하므로 null을 허용하지 않음
  private static Product validateProduct(Product product) {
    if (product == null) {
      throw new IllegalArgumentException("상품은 필수입니다.");
    }
    return product;
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
    //예외1:차감 수량은 1 이상이어야 함 (0 또는 음수 입력 방지)
    if (quantity <= 0) {
      throw new IllegalArgumentException("차감 수량은 1 이상이어야 합니다.");
    }
    //예외2:재고가 주문 수량 보다 적으면 예외
    if (this.stock < quantity) {
      throw new IllegalArgumentException("재고가 부족합니다.");
    }
    //  재고 차감
    this.stock -= quantity;
  }

  // 일정 상태를 CLOSED로 변경 (판매 종료)
  public void close() {
    if (this.status == ScheduleStatus.CLOSED) {
      throw new IllegalStateException("이미 종료된 일정입니다.");
    }
    this.status = ScheduleStatus.CLOSED;
  }

}