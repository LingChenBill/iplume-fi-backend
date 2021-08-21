package com.iplume.fi.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户返回对象.
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
     * token.
     */
    private String token;

    /**
     * 过期时间.
     */
    private Long expire;

}
