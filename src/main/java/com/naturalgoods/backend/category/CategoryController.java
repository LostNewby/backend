package com.naturalgoods.backend.category;

import com.naturalgoods.backend.api.ApiListResponse;
import com.naturalgoods.backend.auth.Language;
import com.naturalgoods.backend.dto.DropDownDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<ApiListResponse<DropDownDto>> filter(@RequestParam Language lang) {
        return ResponseEntity.ok(new ApiListResponse<>(categoryService.categoryList(lang)));
    }
}
