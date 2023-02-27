package com.naturalgoods.backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCardsDto {
    Long id;
    String name;
    String description;
    BigDecimal rating;
    String photo=null;
    Long price;
}
