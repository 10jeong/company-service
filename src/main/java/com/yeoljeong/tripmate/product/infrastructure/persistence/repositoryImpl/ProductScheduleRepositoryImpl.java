package com.yeoljeong.tripmate.product.infrastructure.persistence.repositoryImpl;

import com.yeoljeong.tripmate.product.domain.model.ProductSchedule;
import com.yeoljeong.tripmate.product.domain.repository.ProductScheduleRepository;
import com.yeoljeong.tripmate.product.infrastructure.persistence.jpa.ProductScheduleJpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductScheduleRepositoryImpl implements ProductScheduleRepository {

  private final ProductScheduleJpaRepository jpaRepository;

  @Override
  public ProductSchedule save(ProductSchedule schedule) {
    return jpaRepository.save(schedule);
  }

  @Override
  public List<ProductSchedule> saveAll(List<ProductSchedule> schedules) {
    return jpaRepository.saveAll(schedules);
  }

  @Override
  public Optional<ProductSchedule> findById(UUID id) {
    return jpaRepository.findById(id);
  }

  @Override
  public void flush() {
    jpaRepository.flush();
  }

  @Override
  public Slice<ProductSchedule> findAll(Pageable pageable) {
    return jpaRepository.findAllBy(pageable);
  }

  @Override
  public Slice<ProductSchedule> findAllByProductId(UUID productId, Pageable pageable) {
    return jpaRepository.findAllByProductId(productId, pageable);
  }

  @Override
  public Optional<ProductSchedule> findByIdAndProductId(UUID id, UUID productId) {
    return jpaRepository.findByIdAndProductId(id, productId);
  }
  @Override
  public Slice<ProductSchedule> findAvailableSchedulesByDate(LocalDate date, Pageable pageable) {
    return jpaRepository.findAvailableSchedulesByDate(date, pageable);
  }

  @Override
  public Optional<ProductSchedule> findByProductIdAndId(UUID productId, UUID scheduleId) {
    return jpaRepository.findByProductIdAndId(productId, scheduleId);
  }

}