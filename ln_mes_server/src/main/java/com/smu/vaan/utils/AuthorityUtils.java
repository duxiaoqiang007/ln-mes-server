package com.smu.vaan.utils;

import com.smu.vaan.model.security.AuthorityName;
import com.smu.vaan.security.JwtTokenUtil;
import com.smu.vaan.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by vaan on 2017/3/6.
 * 权限工具类
 */
@Component
public class AuthorityUtils implements Serializable {
    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    public boolean hasRole(HttpServletRequest request, AuthorityName authorityName) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return hasRole(user, authorityName);
    }

    public boolean hasRole(JwtUser user, AuthorityName authorityName) {
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(authorityName.toString()))
                return true;
        }
        return false;
    }




    public JwtUser getUser(UsernamePasswordAuthenticationToken p) throws AccessDeniedException {
        if (null == p.getPrincipal()) {
            throw new AccessDeniedException("");
        } else {
            return (JwtUser) p.getPrincipal();
        }
    }
}
