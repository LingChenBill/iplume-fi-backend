package com.iplume.fi.security;

import com.iplume.fi.model.User;
import com.iplume.fi.utils.TimeProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Jwt helper class.
 *
 * @author: lingchen
 * @date: 2021/8/19
 */
@Component
@Slf4j
public class JwtHelper {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${jwt.secret}")
    public String jwtSecret;

    @Value("${jwt.expires_in}")
    private int jwtExpiresIn;

    @Value("${jwt.header}")
    private String jwtHeader;

    /**
     * 使用平台.
     */
    static final String AUDIENCE_WEB = "web";

    private final TimeProvider timeProvider;

    @Autowired
    public JwtHelper(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

    /**
     * 获取用户.
     *
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
       String userName;
       try {
           final Claims claims = this.getAllClaimsFromToken(token);
           userName = claims.getSubject();
       } catch (Exception e) {
           log.error("fi-auth: JwtHelper: getUserNameFromToken -> {}", e.getMessage());
           userName = null;
       }
       return userName;
    }

    /**
     * 获取IssuedAtDate.
     *
     * @param token
     * @return
     */
    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        } catch (Exception e) {
            log.error("fi-auth: JwtHelper: getIssuedAtDateFromToken -> {}", e.getMessage());
            issueAt = null;
        }
        return issueAt;
    }

    /**
     * 获取Audience.
     *
     * @param token
     * @return
     */
    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            audience = claims.getAudience();
        } catch (Exception e) {
            log.error("fi-auth: JwtHelper: getAudienceFromToken -> {}", e.getMessage());
            audience = null;
        }
        return audience;
    }

    /**
     * 刷新token.
     *
     * @param token
     * @return
     */
    public String refreshToken(String token) {
       String refreshToken;
       Date nowDate = timeProvider.now();
       try {
           final Claims claims = this.getAllClaimsFromToken(token);
           claims.setIssuedAt(nowDate);
           refreshToken = Jwts.builder()
                   .setClaims(claims)
                   .setExpiration(generateExpirationDate())
                   .signWith(signatureAlgorithm, jwtSecret)
                   .compact();
       } catch (Exception e) {
           log.error("fi-auth: JwtHelper: refreshToken -> {}", e.getMessage());
           refreshToken = null;
       }

       return refreshToken;
    }

    /**
     * token生成.
     *
     * @param userName
     * @return
     */
    public String generateToken(String userName) {
        String audience = AUDIENCE_WEB;
        return Jwts.builder()
                .setIssuer(appName)
                .setSubject(userName)
                .setAudience(audience)
                .setIssuedAt(timeProvider.now())
                .setExpiration(generateExpirationDate())
                .signWith(signatureAlgorithm, jwtSecret)
                .compact();
    }

    /**
     * 获取Claims.
     *
     * @param token
     * @return
     */
    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("fi-auth: JwtHelper: getAllClaimsFromToken -> {}", e.getMessage());
            claims = null;
        }
        return claims;
    }

    /**
     * 获取过期日期.
     *
     * @return
     */
    private Date generateExpirationDate() {
        long expiresIn = jwtExpiresIn;
        return new Date(timeProvider.now().getTime() + expiresIn * 1000);
    }

    /**
     * 获取过期时间.
     *
     * @return
     */
    public int getExpiredIn() {
        return jwtExpiresIn;
    }

    /**
     * 从request中获取token.
     *
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        String authHeader = getAuthHeaderFromHeader(request);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    /**
     * 获取jwt Header.
     *
     * @param request
     * @return
     */
    public String getAuthHeaderFromHeader( HttpServletRequest request ) {
        return request.getHeader(jwtHeader);
    }

    /**
     * 验证Token.
     *
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUserNameFromToken(token);
        return (username != null && username.equals(userDetails.getUsername()));
    }

}
