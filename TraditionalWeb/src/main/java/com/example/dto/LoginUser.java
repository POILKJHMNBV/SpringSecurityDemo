package com.example.dto;

import com.example.domain.TbUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhenwu
 */
@Data
public class LoginUser implements UserDetails, Serializable {

    private static final long serialVersionUID = 7737530424262892042L;

    private TbUser user;

    /**
     * 用户的权限信息
     */
    private List<String> permissions;

    /**
     * @return 用户的权限集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 此处设为true是为了通过AbstractUserDetailsAuthenticationProvider(DaoAuthenticationProvider的父类)的校验
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 此处设为true是为了通过AbstractUserDetailsAuthenticationProvider(DaoAuthenticationProvider的父类)的校验
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 此处设为true是为了通过AbstractUserDetailsAuthenticationProvider(DaoAuthenticationProvider的父类)的校验
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 此处设为true是为了通过AbstractUserDetailsAuthenticationProvider(DaoAuthenticationProvider的父类)的校验
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
