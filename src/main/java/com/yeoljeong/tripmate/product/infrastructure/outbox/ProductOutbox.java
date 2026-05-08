package com.yeoljeong.tripmate.product.infrastructure.outbox;

import com.yeoljeong.tripmate.domain.Outbox;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "product_outbox")
public class ProductOutbox extends Outbox {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  public static ProductOutbox create(String topic, String payload) {
    ProductOutbox outbox = new ProductOutbox();
    Outbox.init(outbox, topic, payload);
    return outbox;
  }
}
