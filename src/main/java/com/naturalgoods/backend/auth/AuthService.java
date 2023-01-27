package com.naturalgoods.backend.auth;

import com.naturalgoods.backend.account.UserEntity;
import com.naturalgoods.backend.account.UserRepository;
import com.naturalgoods.backend.dto.RequestUserDto;
import com.naturalgoods.backend.util.JwtProvider;
import com.naturalgoods.backend.util.JwtRequest;
import com.naturalgoods.backend.util.JwtResponse;
import com.naturalgoods.backend.util.PasswordUtils;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Map<String, String> refreshStorage = new HashMap<>();

    public JwtResponse login(@NotNull JwtRequest authRequest) throws AuthException {
        final UserEntity user = userRepository.findByEmail(authRequest.getLogin()).orElseThrow(() -> new AuthException("Данный пользователь не зарегистрирован в системе"));

        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user, authRequest.isRememberMe());
            refreshStorage.put(user.getId().toString(), refreshToken);

            userRepository.save(user);

            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Неверный пароль");
        }
    }

    public void registration(RequestUserDto userDto){
        UserEntity user=new UserEntity();

        // avoid stupid front codes
        userDto.setId(null);

        String password = PasswordUtils.getPassword(8);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setIin(userDto.getIin());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setActive(true);
        user.setTemp(false);

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        user.setTempPwdExpireDte(calendar.getTime());

        userRepository.save(user);
    }

    public JwtResponse getAccessToken(@NotNull String refreshToken) throws AuthException {
        if (!jwtProvider.validateRefreshToken(refreshToken)) {
            throw new AuthException("Refresh token expired");
        }
        final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
        final String login = claims.getSubject();
        final UserEntity user = userRepository.findByEmail(login).orElseThrow(() -> new AuthException("Данный пользователь не зарегистрирован в системе"));

        final String saveRefreshToken = refreshStorage.get(user.getId().toString());
        if (saveRefreshToken == null || !saveRefreshToken.equals(refreshToken)) {
            throw new AuthException("Refresh token invalid");
        }

        final String accessToken = jwtProvider.generateAccessToken(user);
        return new JwtResponse(accessToken, refreshToken);
    }

}
