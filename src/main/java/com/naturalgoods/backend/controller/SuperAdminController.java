package com.naturalgoods.backend.controller;

import com.naturalgoods.backend.api.ApiEmptyResponse;
import com.naturalgoods.backend.api.ApiErrorResponse;
import com.naturalgoods.backend.api.ApiListResponse;
import com.naturalgoods.backend.api.ApiResponse;
import com.naturalgoods.backend.auth.AuthService;
import com.naturalgoods.backend.auth.Role;
import com.naturalgoods.backend.dto.UserInfoDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/superAdmin/")
@AllArgsConstructor
public class SuperAdminController {

    private final AuthService authService;

    @GetMapping("getUserList")
    public ResponseEntity<ApiListResponse<UserInfoDto>> getUsers(@RequestParam String email) {
        return ResponseEntity.ok(new ApiListResponse<>(authService.userList(email, Role.USER)));
    }

    @GetMapping("getAdminList")
    public ResponseEntity<ApiListResponse<UserInfoDto>> getAdmins(@RequestParam String email) {
        return ResponseEntity.ok(new ApiListResponse<>(authService.userList(email, Role.ADMIN)));
    }

    @PostMapping("addOrRemoveAdmin")
    public ResponseEntity<ApiResponse> addOrRemoveAdmin(@RequestParam String email) {
        try {
            authService.grantAdmin(email);
            return ResponseEntity.ok(ApiEmptyResponse.create());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiErrorResponse.create(e.getMessage()));
        }
    }
}
