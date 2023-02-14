package com.naturalgoods.backend.productType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "product_type")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_kz")
    String nameKz;

    @Column(name = "name_ru")
    String nameRu;

    @Column(name = "name_en")
    String nameEn;

    @Column
    Long productId;
}
