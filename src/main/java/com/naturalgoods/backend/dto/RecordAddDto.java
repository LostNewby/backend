package com.naturalgoods.backend.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RecordAddDto {
    private Long id;
    private Long productTypeId;
    private String description;
    private MultipartFile photo;
    private Long price;
    private Long quantity;
    private Long limit;
    private String region;
}
