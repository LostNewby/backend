package com.naturalgoods.backend.util;

import lombok.Data;

@Data
public class JwtRequest {
    String login;
    String password;
    boolean rememberMe = false;
}
