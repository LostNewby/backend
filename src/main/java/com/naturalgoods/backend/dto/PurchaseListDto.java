package com.naturalgoods.backend.dto;

import com.naturalgoods.backend.auth.Language;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class PurchaseListDto {
    private Long id;
    private String email;
    private String costumerPhone;
    private Date purchaseDate;
    private Long price;
    private Map<Language, String> purchaseName;
}
