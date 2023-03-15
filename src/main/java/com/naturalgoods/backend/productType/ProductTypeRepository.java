package com.naturalgoods.backend.productType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long> {
    List<ProductTypeEntity> findAllByProductId(Long productId);
}