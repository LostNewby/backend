package com.naturalgoods.backend.auth;

import com.naturalgoods.backend.api.ApiEmptyResponse;
import com.naturalgoods.backend.api.ApiErrorResponse;
import com.naturalgoods.backend.api.ApiResponse;
import com.naturalgoods.backend.dto.RequestUserDto;
import com.naturalgoods.backend.util.JwtRequest;
import com.naturalgoods.backend.util.JwtResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;

@RestController
@RequestMapping(path = "api/auth")
@CrossOrigin("*")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) {
        try {
            final JwtResponse token = authService.login(authRequest);
            return ResponseEntity.ok(token);
        } catch (AuthException e) {
            return ResponseEntity.badRequest().body(JwtResponse.failed(e.getMessage()));
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<ApiResponse> registration(@RequestBody RequestUserDto request) {
        try {
            authService.registration(request);
            return ResponseEntity.ok(ApiEmptyResponse.create());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiErrorResponse.create(e.getMessage()));
        }
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<ApiResponse> forgotPassword(@RequestBody String mail) {
        try {
            authService.forgotPassword(mail);
            return ResponseEntity.ok(ApiEmptyResponse.create());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiErrorResponse.create(e.getMessage()));
        }
    }

}
