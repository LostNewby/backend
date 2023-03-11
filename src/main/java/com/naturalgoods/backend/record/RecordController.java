package com.naturalgoods.backend.record;

import com.naturalgoods.backend.api.ApiDataResponse;
import com.naturalgoods.backend.dto.FilterDto;
import com.naturalgoods.backend.dto.ProductCardsDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/record")
@AllArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @GetMapping("/filter")
    public ResponseEntity<ApiDataResponse<Page<ProductCardsDto>>> filter(@RequestBody FilterDto filter,
                                                                         @RequestParam Integer page,
                                                                         @RequestParam Integer pageSize) {

        return ResponseEntity.ok(new ApiDataResponse<>(recordService.filter(filter, page, pageSize)));
    }
}
