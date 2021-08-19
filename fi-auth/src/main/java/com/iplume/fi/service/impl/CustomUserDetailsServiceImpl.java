package com.iplume.fi.service.impl;

import com.iplume.fi.dao.FiUserRepository;
import com.iplume.fi.entity.FiUser;
import com.iplume.fi.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author: lingchen
 * @date: 2021/8/19
 */
@Service
@Slf4j
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private FiUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        FiUser result = userRepository.findByLoginEmail(userName);
        if (result == null) {
            log.error("fi-auth: CustomUserDetailsServiceImpl: loadUserByUsername -> {}", "当前用户不存在!");
            return null;
        }

        User user = new User(
                result.getLoginEmail(),
                result.getPassword()
        );

        return user;
    }
}
