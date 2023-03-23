package com.naturalgoods.backend.purchase;

import com.naturalgoods.backend.api.ApiDataResponse;
import com.naturalgoods.backend.api.ApiEmptyResponse;
import com.naturalgoods.backend.api.ApiErrorResponse;
import com.naturalgoods.backend.api.ApiResponse;
import com.naturalgoods.backend.dto.CostumerListResponse;
import com.naturalgoods.backend.dto.PurchaseRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/purchase")
@AllArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping("/request")
    public ResponseEntity<ApiResponse> purchaseRequest(@RequestBody List<PurchaseRequestDto> purchaseRequestDtoList) {
        try {
            purchaseService.purchaseRequest(purchaseRequestDtoList);
            return ResponseEntity.ok(ApiEmptyResponse.create());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiErrorResponse.create(e.getMessage()));
        }
    }

    @GetMapping("/list")
    public ResponseEntity<ApiDataResponse<Map<String, List<CostumerListResponse>>>> getCostumerList() {
        return ResponseEntity.ok(new ApiDataResponse<>(purchaseService.getCostumerList()));
    }
}
