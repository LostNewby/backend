package com.naturalgoods.backend.controller;

import com.naturalgoods.backend.api.ApiEmptyResponse;
import com.naturalgoods.backend.api.ApiErrorResponse;
import com.naturalgoods.backend.api.ApiResponse;
import com.naturalgoods.backend.auth.AuthService;
import com.naturalgoods.backend.auth.Language;
import com.naturalgoods.backend.dto.RequestUserDto;
import com.naturalgoods.backend.util.JwtRequest;
import com.naturalgoods.backend.util.JwtResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;

@RestController
@RequestMapping(path = "api/auth/")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest, @RequestParam(required = false, defaultValue = "RU") Language lang) {
        try {
            final JwtResponse token = authService.login(authRequest, lang);
            return ResponseEntity.ok(token);
        } catch (AuthException e) {
            return ResponseEntity.badRequest().body(JwtResponse.failed(e.getMessage()));
        }
    }

    @PostMapping("registration")
    public ResponseEntity<ApiResponse> registration(@RequestBody RequestUserDto request, @RequestParam(required = false, defaultValue = "RU") Language lang) {
        try {
            authService.registration(request, lang);
            return ResponseEntity.ok(ApiEmptyResponse.create());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiErrorResponse.create(e.getMessage()));
        }
    }

    @PostMapping("forgotPassword")
    public ResponseEntity<ApiResponse> forgotPassword(@RequestParam String mail, @RequestParam(required = false, defaultValue = "RU") Language lang) {
        try {
            authService.forgotPassword(mail, lang);
            return ResponseEntity.ok(ApiEmptyResponse.create());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiErrorResponse.create(e.getMessage()));
        }
    }

    @PostMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestParam String refreshToken) {
        final JwtResponse token;
        try {
            token = authService.getAccessToken(refreshToken);
        } catch (AuthException e) {
            return ResponseEntity.badRequest().body(JwtResponse.failed(e.getMessage()));
        }
        return ResponseEntity.ok(token);
    }

}
