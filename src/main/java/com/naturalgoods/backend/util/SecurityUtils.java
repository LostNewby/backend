package com.naturalgoods.backend.util;


import com.naturalgoods.backend.config.JwtAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

public class SecurityUtils {

    public static JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

    public static Long getCurrentId() {
        JwtAuthentication authInfo = getAuthInfo();
        return (Long) ((Map<String, Object>) authInfo.getDetails()).get("id");
    }
}
