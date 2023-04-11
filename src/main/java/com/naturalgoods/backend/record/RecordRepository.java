package com.naturalgoods.backend.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
    @Query(value = "select r from RecordEntity r where r.userId=?1 and r.quantity<r.limit")
    List<RecordEntity> findAllByUserIdAndQuantityLessThanLimit(Long id);

    @Query(value = "select r from RecordEntity r where r.userId=?1 and r.quantity>=r.limit")
    List<RecordEntity> findAllByUserIdAndQuantityMoreThanLimit(Long id);
}