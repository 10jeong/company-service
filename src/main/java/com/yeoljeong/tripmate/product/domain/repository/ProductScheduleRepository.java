package com.yeoljeong.tripmate.product.domain.repository;

import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductScheduleRepository {

  ProductSchedule save(ProductSchedule schedule);

  List<ProductSchedule> saveAll(List<ProductSchedule> schedules);

  Optional<ProductSchedule> findById(UUID id);

  void flush();

}
