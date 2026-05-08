package com.yeoljeong.tripmate.product.application.port;

import java.util.UUID;

public interface ProductStockEventPort {
  void save(UUID planUnitId, UUID userId, int quantity);
}
