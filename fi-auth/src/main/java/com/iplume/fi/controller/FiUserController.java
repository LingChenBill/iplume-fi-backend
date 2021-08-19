package com.iplume.fi.controller;

import com.alibaba.fastjson.JSON;
import com.iplume.fi.exception.FiException;
import com.iplume.fi.service.FiUserService;
import com.iplume.fi.vo.FiUserRequest;
import com.iplume.fi.vo.FiUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制类.
 *
 * @author: lingchen
 * @date: 2021/8/19
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class FiUserController {

    private final FiUserService userService;

    @Autowired
    public FiUserController(FiUserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录.
     *
     * @param request
     * @return
     */
    @PostMapping("/login")
    public FiUserResponse login(@RequestBody FiUserRequest request) throws FiException {
        log.info("fi-auth: login -> {}", JSON.toJSONString(request));
        return userService.login(request);
    }

}
