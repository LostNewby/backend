package com.naturalgoods.backend.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "category")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_kz")
    String nameKz;

    @Column(name = "name_ru")
    String nameRu;

    @Column(name = "name_en")
    String nameEn;
}
