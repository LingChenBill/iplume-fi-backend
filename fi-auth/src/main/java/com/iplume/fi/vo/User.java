package com.iplume.fi.vo;

import com.iplume.fi.entity.FiAuthority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * user对象.
 *
 * @author: lingchen
 * @date: 2021/8/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    /**
     * 邮件名称.
     */
    private String loginEmail;

    /**
     * 密码.
     */
    private String password;

    /**
     * 权限.
     */
    private List<FiAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.loginEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
