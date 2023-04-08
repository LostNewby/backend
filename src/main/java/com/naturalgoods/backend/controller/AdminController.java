package com.naturalgoods.backend.controller;

import com.naturalgoods.backend.api.ApiEmptyResponse;
import com.naturalgoods.backend.api.ApiErrorResponse;
import com.naturalgoods.backend.api.ApiListResponse;
import com.naturalgoods.backend.api.ApiResponse;
import com.naturalgoods.backend.auth.AuthService;
import com.naturalgoods.backend.auth.Language;
import com.naturalgoods.backend.auth.Role;
import com.naturalgoods.backend.dto.UserInfoDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/admin/")
@AllArgsConstructor
public class AdminController {
    private final AuthService authService;

    @PostMapping("blackListUser")
    public ResponseEntity<ApiResponse> banUser(@RequestParam String email, @RequestParam(required = false, defaultValue = "RU") Language lang) {
        try {
            authService.blackList(email, lang);
            return ResponseEntity.ok(ApiEmptyResponse.create());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiErrorResponse.create(e.getMessage()));
        }
    }

    @GetMapping("getUserList")
    public ResponseEntity<ApiListResponse<UserInfoDto>> getUsers(){
        return ResponseEntity.ok(new ApiListResponse<>(authService.userList(Role.USER)));
    }

}
