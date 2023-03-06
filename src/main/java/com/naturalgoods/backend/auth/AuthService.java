package com.naturalgoods.backend.auth;

import com.naturalgoods.backend.account.UserEntity;
import com.naturalgoods.backend.account.UserRepository;
import com.naturalgoods.backend.auth.email.EmailService;
import com.naturalgoods.backend.dto.RequestUserDto;
import com.naturalgoods.backend.mappers.RequestUserMapper;
import com.naturalgoods.backend.util.*;
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
    private final AuthExceptions authExceptions;

    public JwtResponse login(@NotNull JwtRequest authRequest, Language language) throws AuthException {
        UserEntity user = userRepository.findByEmailOrPhoneNumber(authRequest.getLogin()).orElseThrow(() -> authExceptions.authExceptions(language));

        if(!user.isActive()){
            throw authExceptions.blackList(language);
        }

        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user, authRequest.isRememberMe());
            refreshStorage.put(user.getId().toString(), refreshToken);

            userRepository.save(user);

            JwtResponse response = new JwtResponse(accessToken, refreshToken);
            response.setRole(user.getRole());
            return response;
        } else {
            throw authExceptions.incorrectPassword(language);
        }
    }

    public void registration(RequestUserDto userDto, Language language) throws Exception {
        Optional<UserEntity> userValidation = userRepository.findByEmailOrPhoneNumber(userDto.getEmail());

        if(userValidation.isPresent()){
            throw authExceptions.alreadyExist(language);
        }

        UserEntity user= RequestUserMapper.MAPPER.mapToEntity(userDto);

        user.setId(null);

        String password = PasswordUtils.getPassword(8);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("user");
        user.setActive(true);

        user=userRepository.save(user);

        emailService.sendNewPassword(user.getFirstName(), password, user.getEmail());
    }

    public void changeUserInfo(RequestUserDto userInfo, Language language) throws Exception {
        UserEntity user = userRepository.findByEmail(SecurityUtils.getAuthInfo().getUsername()).orElseThrow(() -> authExceptions.authExceptions(language));

        user.setFirstName(userInfo.getFirstName());
        user.setLastName(userInfo.getLastName());
        user.setPhoneNumber(userInfo.getPhoneNumber());

        userRepository.save(user);
    }

    public void forgotPassword(String detail, Language language) throws AuthException {
        UserEntity user = userRepository.findByEmailOrPhoneNumber(detail).orElseThrow(() -> authExceptions.authExceptions(language));

        String password = PasswordUtils.getPassword(8);
        user.setPassword(passwordEncoder.encode(password));

        user=userRepository.save(user);

        emailService.sendNewPassword(user.getFirstName(), password, user.getEmail());
    }

    public void changePassword(String password, Language language) throws AuthException {
        UserEntity user = userRepository.findByEmail(SecurityUtils.getAuthInfo().getUsername()).orElseThrow(() -> authExceptions.authExceptions(language));

        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
    }

    public void blackList(String detail, Language language) throws AuthException {
        UserEntity user = userRepository.findByEmailOrPhoneNumber(detail).orElseThrow(() -> authExceptions.authExceptions(language));

        user.setActive(false);

        user=userRepository.save(user);

        emailService.sendBanNotice(user.getFirstName(), user.getEmail());
    }

    public JwtResponse getAccessToken(@NotNull String refreshToken) throws AuthException {
        if (!jwtProvider.validateRefreshToken(refreshToken)) {
            throw new AuthException("Refresh token expired");
        }
        final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
        final String login = claims.getSubject();
        final UserEntity user = userRepository.findByEmail(login).orElseThrow(() -> authExceptions.authExceptions(Language.EN));

        final String saveRefreshToken = refreshStorage.get(user.getId().toString());
        if (saveRefreshToken == null || !saveRefreshToken.equals(refreshToken)) {
            throw new AuthException("Refresh token invalid");
        }

        final String accessToken = jwtProvider.generateAccessToken(user);
        return new JwtResponse(accessToken, refreshToken);
    }

}
