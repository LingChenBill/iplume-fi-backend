package com.iplume.fi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author: lingchen
 * @date: 2021/8/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiUserResponse {

    /**
     * 邮件名称.
     */
    private String loginEmail;

    /**
     * 密码.
     */
    private String password;

    /**
     * token.
     */
    private String token;

    /**
     * 过期时间.
     */
    private int expire;

}
