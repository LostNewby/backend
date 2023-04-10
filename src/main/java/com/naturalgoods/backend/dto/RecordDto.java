package com.naturalgoods.backend.dto;

import lombok.Data;

@Data
public class RecordDto {
    private Long id;
    private String name;
    private Long productTypeId;
    private String description;
    private String photo;
    private Long price;
    private Long quantity;
    private Long limit;
    private String region;
}
