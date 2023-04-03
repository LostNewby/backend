package com.naturalgoods.backend.controller;

import com.naturalgoods.backend.api.ApiListResponse;
import com.naturalgoods.backend.auth.Language;
import com.naturalgoods.backend.dto.DropDownDto;
import com.naturalgoods.backend.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<ApiListResponse<DropDownDto>> filter(@RequestParam Long categoryId,
                                                               @RequestParam Language lang) {
        return ResponseEntity.ok(new ApiListResponse<>(productService.productList(categoryId, lang)));
    }
}
