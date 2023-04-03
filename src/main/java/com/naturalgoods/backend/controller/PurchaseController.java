package com.naturalgoods.backend.controller;

import com.naturalgoods.backend.api.ApiEmptyResponse;
import com.naturalgoods.backend.api.ApiErrorResponse;
import com.naturalgoods.backend.api.ApiListResponse;
import com.naturalgoods.backend.api.ApiResponse;
import com.naturalgoods.backend.dto.PurchaseListDto;
import com.naturalgoods.backend.dto.PurchaseRequestDto;
import com.naturalgoods.backend.purchase.PurchaseService;
import com.naturalgoods.backend.purchase.PurchaseStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ApiListResponse<PurchaseListDto>> getCostumerList(@RequestParam PurchaseStatus status) {
        return ResponseEntity.ok(new ApiListResponse<>(purchaseService.getCostumerList(status)));
    }

    @GetMapping("/purchaseList")
    public ResponseEntity<ApiListResponse<PurchaseListDto>> getPurchaseList(@RequestParam PurchaseStatus status) {
        return ResponseEntity.ok(new ApiListResponse<>(purchaseService.getPurchaseList(status)));
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
