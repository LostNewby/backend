package com.naturalgoods.backend.util;

import com.naturalgoods.backend.account.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${jwtRefreshExpirationMs}")
    private int jwtRefreshExpirationMs;

    public String generateAccessToken(@NonNull UserEntity userEntity) {
        final Date accessExpiration = new Date((new Date()).getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(userEntity.getFirstName())
                .setExpiration(accessExpiration)
                .claim("id", userEntity.getId())
                .claim("firstName", userEntity.getFirstName())
                .claim("lastName", userEntity.getLastName())
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                .compact();
    }

    public String generateRefreshToken(@NonNull UserEntity userEntity, boolean rememberMe) {
        int ttl = jwtRefreshExpirationMs;
        if (rememberMe) {
            ttl = ttl * 30;
        }
        final Date refreshExpiration = new Date((new Date()).getTime() + ttl);
        return Jwts.builder()
                .setSubject(userEntity.getFirstName())
                .setExpiration(refreshExpiration)
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                .compact();
    }

    public boolean validateAccessToken(@NonNull String accessToken) {
        return validateToken(accessToken, Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)));
    }

    public boolean validateRefreshToken(@NonNull String refreshToken) {
        return validateToken(refreshToken, Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)));
    }

    private boolean validateToken(@NonNull String token, @NonNull Key secret) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired", expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt", mjEx);
        } catch (SignatureException sEx) {
            log.error("Invalid signature", sEx);
        } catch (Exception e) {
            log.error("invalid token", e);
        }
        return false;
    }

    public Claims getAccessClaims(@NonNull String token) {
        return getClaims(token, Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)));
    }

    public Claims getRefreshClaims(@NonNull String token) {
        return getClaims(token, Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)));
    }

    private Claims getClaims(@NonNull String token, @NonNull Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}