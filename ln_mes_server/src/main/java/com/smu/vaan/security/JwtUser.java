package com.smu.vaan.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtUser implements UserDetails {

    private final String id;
    private final String username;
    private final String cy;
    private final String password;
    private final String department;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;
    private final String tel;
    private final Date lastPasswordResetDate;

    public JwtUser(
            String id,
            String username,
            String cy,
            String password,
            String department,
            Collection<? extends GrantedAuthority> authorities,
            boolean enabled,
            String tel,
            Date lastPasswordResetDate
    ) {
        this.id = id;
        this.username = username;
        this.cy = cy;
        this.password = password;
        this.department = department;
        this.authorities = authorities;
        this.enabled = enabled;
        this.tel = tel;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    public String getDepartment() {
        return department;
    }

    public String getTel() {
        return tel;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
}
