package com.iplume.fi;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: lingchen
 * @date: 2021/8/19
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthApplication.class)
public class JwtHelperTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testPasswordEncoder() {
        String pwd = "Aa123456";
        String encode = passwordEncoder.encode(pwd);
        log.info("Encode: {}", encode);

        String encode2 = passwordEncoder.encode(pwd);
        log.info("Encode: {}", encode2);
        if (!passwordEncoder.matches(pwd, encode)) {
            log.info("密码不匹配.");
        }
    }
}
