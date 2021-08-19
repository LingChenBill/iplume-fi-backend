package com.iplume.fi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * fi_user表对象.
 *
 * @author: lingchen
 * @date: 2021/8/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fi_user")
public class FiUser {

    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 邮件名称.
     */
    @Basic
    @Column(name = "login_email", nullable = false)
    private String loginEmail;

    /**
     * 密码.
     */
    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * 创建时间.
     */
    @Basic
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /**
     * 更新时间.
     */
    @Basic
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "fi_user_authority",
                joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<FiAuthority> authorities;

    /**
     * FiUser对象生成处理.
     *
     * @param loginEmail 邮件名称.
     * @param password   密码.
     */
    public FiUser(String loginEmail, String password) {
        this.loginEmail = loginEmail;
        this.password = password;
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}
