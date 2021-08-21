package com.iplume.fi.service;

import com.iplume.fi.exception.FiException;
import com.iplume.fi.model.user.FiUserRequest;
import com.iplume.fi.model.user.FiUserResponse;

/**
 * @author: lingchen
 * @date: 2021/8/19
 */
public interface FiUserService {

    /**
     * 登陆处理.
     *
     * @param request
     * @return
     * @throws FiException
     */
    FiUserResponse login(FiUserRequest request) throws FiException;

}
