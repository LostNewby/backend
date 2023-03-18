package com.naturalgoods.backend.rating;

import com.naturalgoods.backend.api.ApiEmptyResponse;
import com.naturalgoods.backend.api.ApiErrorResponse;
import com.naturalgoods.backend.api.ApiListResponse;
import com.naturalgoods.backend.api.ApiResponse;
import com.naturalgoods.backend.dto.RatingRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/rating/")
@AllArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping("post")
    public ResponseEntity<ApiResponse> ratingPost(@RequestBody RatingRequestDto ratingRequestDto) {
        try {
            ratingService.postRating(ratingRequestDto);
            return ResponseEntity.ok(new ApiEmptyResponse());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("get")
    public ResponseEntity<ApiResponse> ratingGet(@RequestParam Long recordId) {
        try {
            return ResponseEntity.ok(new ApiListResponse<>(ratingService.getRating(recordId)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiErrorResponse(e.getMessage()));
        }
    }
}
