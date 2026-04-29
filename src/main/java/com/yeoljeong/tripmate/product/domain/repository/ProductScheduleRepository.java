package com.yeoljeong.tripmate.product.domain.repository;

import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ProductScheduleRepository {

  ProductSchedule save(ProductSchedule schedule);

  List<ProductSchedule> saveAll(List<ProductSchedule> schedules);

  Optional<ProductSchedule> findById(UUID id);

  Slice<ProductSchedule> findAll(Pageable pageable);

  void flush();

  Slice<ProductSchedule> findAllByProductId(UUID productId, Pageable pageable);

  Optional<ProductSchedule> findByIdAndProductId(UUID id, UUID productId);

  List<ProductSchedule> findAvailableSchedulesByDate(LocalDate date);
}
