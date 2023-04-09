package com.naturalgoods.backend.dto;

import com.naturalgoods.backend.productType.Unit;
import lombok.Data;

import java.util.Date;

@Data
public class SellerListResponse {
    private Long purchaseId;
    private String purchaseNameKz;
    private String purchaseNameRu;
    private String purchaseNameEn;
    private Unit unit;
    private Date purchaseDate;
    private Long price;
    private Integer quantity;
    private String seller;
    private String costumerPhone;
}
