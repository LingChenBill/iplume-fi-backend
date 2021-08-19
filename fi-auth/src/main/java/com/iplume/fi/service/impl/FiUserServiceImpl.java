package com.iplume.fi.service.impl;

import com.iplume.fi.dao.FiUserRepository;
import com.iplume.fi.entity.FiUser;
import com.iplume.fi.exception.FiException;
import com.iplume.fi.model.User;
import com.iplume.fi.service.FiUserService;
import com.iplume.fi.utils.AesUtil;
import com.iplume.fi.security.JwtHelper;
import com.iplume.fi.vo.FiUserRequest;
import com.iplume.fi.vo.FiUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${aes.encrypt_key}")
    private String encryptKey;

    @Autowired
    private FiUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        int expires = jwtHelper.getExpiredIn();

        return new FiUserResponse(
                request.getLoginEmail(),
                "",
                token,
                expires
        );
    }
}
