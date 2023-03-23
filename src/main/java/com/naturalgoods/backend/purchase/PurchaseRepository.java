package com.naturalgoods.backend.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
    @Query(value = "select pr.costumer_id, pt.name_kz, pt.name_ru, pt.name_en, pr.quantity, r.price, pr.costumer_phone, pr.creation_date " +
            "from purchase_info pr " +
            "left join records r on pr.record_id=r.id " +
            "left join product_type pt on r.product_type_id=pt.id  " +
            "where r.user_id = ?1", nativeQuery = true)
    List<Object[]> findBySellerId(Long id);
}