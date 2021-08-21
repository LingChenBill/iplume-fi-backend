package com.iplume.fi.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户对象请求类.
 *
 * @author: lingchen
 * @date: 2021/8/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiUserRequest {

    /**
     * 邮件名称.
     */
    private String loginEmail;

    /**
     * 密码.
     */
    private String password;
}
