package com.naturalgoods.backend.dto;

import lombok.Data;

@Data
public class RatingResponseDto {
    private Long id;
    private String userName;
    private Integer rating;
    private String description;
}
