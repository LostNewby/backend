package com.naturalgoods.backend.dto;

import lombok.Data;

@Data
public class RatingRequestDto {
    private Long recordId;
    private Integer rating;
    private String description;
}
