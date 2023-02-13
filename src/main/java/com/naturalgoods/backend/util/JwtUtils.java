package com.naturalgoods.backend.util;

import com.naturalgoods.backend.config.JwtAuthentication;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setFirstName(claims.get("firstName", String.class));
        jwtInfoToken.setUsername(claims.get("email", String.class));
        if (jwtInfoToken.getDetails() instanceof Map) {
            ((Map<String, Object>) jwtInfoToken.getDetails()).put("id", claims.get("id", Long.class));
        }
        return jwtInfoToken;
    }

}