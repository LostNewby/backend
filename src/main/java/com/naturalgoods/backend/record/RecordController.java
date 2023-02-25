package com.naturalgoods.backend.record;

import com.naturalgoods.backend.api.ApiListResponse;
import com.naturalgoods.backend.dto.ProductCardsDto;
import com.naturalgoods.backend.record.enums.SortingType;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/record")
@AllArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @GetMapping("/filter")
    public ResponseEntity<ApiListResponse<ProductCardsDto>> filter(@RequestParam(required = false) String name,
                                                        @RequestParam(required = false) List<Long> categoryId,
                                                        @RequestParam(required = false) List<Long> productId,
                                                        @RequestParam(required = false) List<Long> productTypeId,
                                                        @RequestParam(required = false, defaultValue = "PRICE") SortingType sortingType,
                                                        @RequestParam(required = false, defaultValue = "0" ) Long minPrice,
                                                        @RequestParam(required = false) Long maxPrice,
                                                        @RequestParam String region){

        return ResponseEntity.ok(new ApiListResponse<>(recordService.filter(name,  categoryId, productId, productTypeId, sortingType, minPrice, maxPrice, region)));
    }
}
