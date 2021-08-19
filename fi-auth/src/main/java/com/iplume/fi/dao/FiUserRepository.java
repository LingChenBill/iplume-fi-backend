package com.iplume.fi.dao;

import com.iplume.fi.entity.FiUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * fiuser repository.
 *
 * @author: lingchen
 * @date: 2021/8/19
 */
public interface FiUserRepository extends JpaRepository<FiUser, Long> {

    /**
     * 按用户账号和密码来查找用户.
     *
     * @param loginEmail
     * @param password
     * @return
     */
    FiUser findByLoginEmailAndAndPassword(String loginEmail, String password);

    /**
     * 按用户账号来查找用户.
     *
     * @param loginEmail
     * @return
     */
    FiUser findByLoginEmail(String loginEmail);
}
