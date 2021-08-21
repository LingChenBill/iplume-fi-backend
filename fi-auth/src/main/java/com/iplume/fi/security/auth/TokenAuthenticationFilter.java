package com.iplume.fi.security.auth;

import com.iplume.fi.security.JwtHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT过滤器，整个程序要依赖它来识别jwt跟用户.
 *
 * @author: lingchen
 * @date: 2021/8/19
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private JwtHelper jwtHelper;

    private UserDetailsService userDetailsService;

    public TokenAuthenticationFilter(JwtHelper jwtHelper, UserDetailsService userDetailsService) {
        this.jwtHelper = jwtHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String userName;
        // 从request中获取token.
        String authToken = jwtHelper.getToken(request);

        if (!StringUtils.isEmpty(authToken)) {
            // 从token中获取用户名.
            userName = jwtHelper.getUserNameFromToken(authToken);
            if (userName != null) {
                // get user.
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                if (jwtHelper.validateToken(authToken, userDetails)) {
                    // create authentication.
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                    authentication.setToken(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
