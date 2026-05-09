package com.yeoljeong.tripmate.product.application.port;

import java.util.UUID;

public interface ProductStockEventPort {
  void save(UUID planUnitId, UUID orderId , UUID userId, int quantity);
}
