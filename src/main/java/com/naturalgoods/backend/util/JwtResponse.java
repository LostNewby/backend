package com.naturalgoods.backend.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponse {

    private boolean success = true;
    private String type = "Bearer";
    private String accessToken;
    private String refreshToken;
    private String msg;
    private boolean needToChangePassword = false;

    public JwtResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static JwtResponse failed(String msg) {
        JwtResponse authResult = new JwtResponse();
        authResult.setMsg(msg);
        authResult.setSuccess(false);
        return authResult;
    }

    public static JwtResponse needChangePwd() {
        JwtResponse authResult = new JwtResponse();
        authResult.setNeedToChangePassword(true);
        return authResult;
    }
}