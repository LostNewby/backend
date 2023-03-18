package com.naturalgoods.backend.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {
    List<RatingEntity> findAllByRecordId(Long recordId);

    @Query(value = "select r.*, u.first_name from ratings r left join users u on r.user_id = u.id where r.record_id = ?1", nativeQuery=true)
    List<Object[]> findAllObjByRecordId(Long recordId);
}