package com.iplume.fi.service.impl;

import com.iplume.fi.exception.FiException;
import com.iplume.fi.vo.User;
import com.iplume.fi.security.JwtHelper;
import com.iplume.fi.service.FiUserService;
import com.iplume.fi.model.user.FiUserRequest;
import com.iplume.fi.model.user.FiUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类.
 *
 * @author: lingchen
 * @date: 2021/8/19
 */
@Service
@Slf4j
public class FiUserServiceImpl implements FiUserService {

    @Value("${aes.encrypt_key}")
    private String encryptKey;

    private final JwtHelper jwtHelper;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public FiUserServiceImpl(JwtHelper jwtHelper,
                             AuthenticationManager authenticationManager,
                             PasswordEncoder passwordEncoder) {
        this.jwtHelper = jwtHelper;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 登录处理.
     *
     * @param request
     * @return
     */
    @Override
    public FiUserResponse login(FiUserRequest request) throws FiException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLoginEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.info("fi-auth: login -> {}", "用户密码不匹配.");
            return null;
        }

        String token = jwtHelper.generateToken(user.getUsername());

        long expires = jwtHelper.getExpiredIn();

        return new FiUserResponse(
                request.getLoginEmail(),
                token,
                expires
        );
    }
}
