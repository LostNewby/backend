package com.naturalgoods.backend.purchase;

import com.naturalgoods.backend.api.ApiDataResponse;
import com.naturalgoods.backend.api.ApiEmptyResponse;
import com.naturalgoods.backend.api.ApiErrorResponse;
import com.naturalgoods.backend.api.ApiResponse;
import com.naturalgoods.backend.dto.CostumerListResponse;
import com.naturalgoods.backend.dto.PurchaseRequestDto;
import com.naturalgoods.backend.dto.SellerListResponse;
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

    @GetMapping("/customerList")
    public ResponseEntity<ApiDataResponse<Map<String, List<CostumerListResponse>>>> getCostumerList(@RequestParam PurchaseStatus status) {
        return ResponseEntity.ok(new ApiDataResponse<>(purchaseService.getCostumerList(status)));
    }

    @GetMapping("/purchaseList")
    public ResponseEntity<ApiDataResponse<Map<String, List<SellerListResponse>>>> getPurchaseList(@RequestParam PurchaseStatus status) {
        return ResponseEntity.ok(new ApiDataResponse<>(purchaseService.getPurchaseList(status)));
    }

    @PostMapping("/changeStatus")
    public ResponseEntity<ApiResponse> changeStatus(@RequestParam Long purchaseId,
            @RequestParam PurchaseStatus purchaseStatus) {
        try {
            purchaseService.changePurchaseStatus(purchaseId, purchaseStatus);
            return ResponseEntity.ok(ApiEmptyResponse.create());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiErrorResponse.create(e.getMessage()));
        }
    }
}
