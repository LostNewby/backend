package com.naturalgoods.backend.rating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Table(name = "ratings")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "record_id")
    Long recordId;

    @Column(name = "ratings_stars")
    BigDecimal ratingStars;

    @Column
    String description;
}
