package com.naturalgoods.backend.record;

import com.naturalgoods.backend.record.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Table(name = "records")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class    RecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "product_type_id")
    Long productTypeId;

    @Column
    BigDecimal rating;

    @Column
    Long price;

    @Column
    Long quantity;

    @Column(name = "limit_to_buy")
    Long limit;

    @Column
    String region;

    @Column
    String description;

    @Column
    @Enumerated(EnumType.STRING)
    Unit unit;
}
