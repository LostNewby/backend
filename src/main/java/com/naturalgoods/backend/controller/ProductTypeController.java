package com.naturalgoods.backend.controller;

import com.naturalgoods.backend.api.ApiListResponse;
import com.naturalgoods.backend.auth.Language;
import com.naturalgoods.backend.dto.DropDownDto;
import com.naturalgoods.backend.productType.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/productType")
@AllArgsConstructor
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    @GetMapping("/list")
    public ResponseEntity<ApiListResponse<DropDownDto>> filter(@RequestParam Long productId,
                                                               @RequestParam Language lang) {
        return ResponseEntity.ok(new ApiListResponse<>(productTypeService.productTypeList(productId, lang)));
    }
}
