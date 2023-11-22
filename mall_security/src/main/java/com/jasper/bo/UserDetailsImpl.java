package com.jasper.bo;

import com.jasper.pojo.User;
import com.jasper.pojo.UserResource;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author com
 * 6/22/2023  3:48 PM
 * 用户信息封装类
 */
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final User user;

    private final List<UserResource> resourceList;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return resourceList.stream().
                map(role -> new SimpleGrantedAuthority(role.getId()+":"+role.getName())).toList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
        return user.getStatus().equals(1);
    }
}
