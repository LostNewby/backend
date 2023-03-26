package com.naturalgoods.backend.record;

import com.naturalgoods.backend.productType.Unit;
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
public class RecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_type_id")
    private Long productTypeId;

    @Column
    private BigDecimal rating;

    @Column
    private Long price;

    @Column
    private Long quantity;

    @Column(name = "limit_to_buy")
    private Long limit;

    @Column
    private String region;

    @Column
    private String description;

}
