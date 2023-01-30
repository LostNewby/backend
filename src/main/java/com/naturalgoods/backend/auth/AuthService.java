package com.naturalgoods.backend.auth;

import com.naturalgoods.backend.account.UserEntity;
import com.naturalgoods.backend.account.UserRepository;
import com.naturalgoods.backend.auth.email.EmailService;
import com.naturalgoods.backend.dto.RequestUserDto;
import com.naturalgoods.backend.mappers.RequestUserMapper;
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
import java.util.*;

@Service
@AllArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final EmailService emailService;

    public JwtResponse login(@NotNull JwtRequest authRequest) throws AuthException {
        UserEntity user = userRepository.findByEmail(authRequest.getLogin()).orElseThrow(() -> new AuthException("Данный пользователь не зарегистрирован в системе"));

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

    public void registration(RequestUserDto userDto) throws Exception {
        Optional<UserEntity> userValidation = userRepository.findByEmailOrPhoneNumberOrIin(userDto.getEmail());

        if(userValidation.isPresent()){
            throw new Exception("Пользователь с данными уже зарегистрирован в системе");
        }

        UserEntity user= RequestUserMapper.MAPPER.mapToEntity(userDto);

        user.setId(null);

        String password = PasswordUtils.getPassword(8);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);
        user.setTemp(true);

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        user.setTempPwdExpireDte(calendar.getTime());

        user=userRepository.save(user);

        emailService.sendNewPassword(user.getFirstName(), password, user.getEmail());
    }

    public void forgotPassword(String detail) throws AuthException {
        UserEntity user = userRepository.findByEmailOrPhoneNumberOrIin(detail).orElseThrow(() -> new AuthException("Данный пользователь не зарегистрирован в системе"));

        String password = PasswordUtils.getPassword(8);
        user.setPassword(passwordEncoder.encode(password));
        user.setTemp(true);

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        user.setTempPwdExpireDte(calendar.getTime());

        user=userRepository.save(user);

        emailService.sendNewPassword(user.getFirstName(), password, user.getEmail());
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
