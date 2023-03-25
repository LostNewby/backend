package com.naturalgoods.backend.dto;

import lombok.Data;

@Data
public class PurchaseRequestDto {
    private Long recordId;
    private Integer quantity;
    private String phoneNumber;
}
