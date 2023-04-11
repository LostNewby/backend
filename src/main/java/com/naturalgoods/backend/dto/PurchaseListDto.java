package com.naturalgoods.backend.dto;

import com.naturalgoods.backend.auth.Language;
import lombok.Data;

import java.util.Map;

@Data
public class PurchaseListDto {
    private Long id;
    private String email;
    private String costumerPhone;
    private String purchaseDate;
    private Long price;
    private Map<Language, String> purchaseName;
}
