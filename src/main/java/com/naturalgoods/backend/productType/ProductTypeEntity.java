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
    private String nameKz;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "name_en")
    private String nameEn;

    @Column
    private Long productId;

    @Column(name = "unit")
    @Enumerated(EnumType.STRING)
    private Unit unit;
}
