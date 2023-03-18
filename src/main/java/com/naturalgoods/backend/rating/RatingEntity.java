package com.naturalgoods.backend.rating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "ratings")
@AllArgsConstructor
@NoArgsConstructor
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "record_id")
    private Long recordId;

    @Column(name = "rating_stars")
    private Integer rating;

    @Column(name = "description")
    private String description;
}
