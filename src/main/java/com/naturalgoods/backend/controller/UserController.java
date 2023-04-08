package com.naturalgoods.backend.controller;

import com.naturalgoods.backend.api.ApiEmptyResponse;
import com.naturalgoods.backend.api.ApiErrorResponse;
import com.naturalgoods.backend.api.ApiResponse;
import com.naturalgoods.backend.auth.AuthService;
import com.naturalgoods.backend.auth.Language;
import com.naturalgoods.backend.dto.RequestUserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/user/")
@AllArgsConstructor
public class UserController {
    private final AuthService authService;

    @PostMapping("changeUserInfo")
    public ResponseEntity<ApiResponse> changeUserInfo(@RequestBody RequestUserDto userInfo, @RequestParam(required = false, defaultValue = "RU") Language lang) {
        try {
            authService.changeUserInfo(userInfo, lang);
            return ResponseEntity.ok(ApiEmptyResponse.create());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiErrorResponse.create(e.getMessage()));
        }
    }

    @PostMapping("changePassword")
    public ResponseEntity<ApiResponse> changePassword(@RequestParam String password, @RequestParam(required = false, defaultValue = "RU") Language lang) {
        try {
            authService.changePassword(password, lang);
            return ResponseEntity.ok(ApiEmptyResponse.create());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiErrorResponse.create(e.getMessage()));
        }
    }
}
