package com.jgchuanmei.admng.security;

import com.jgchuanmei.admng.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails {
    private String name;
    private String username;
    private String password;
    private String phoneNumber;
    private List<GrantedAuthority> authorities;

    private boolean isAccountNonLocked;

    private String topAuthority;

    private User user;

    public UserInfoUserDetails(User user) {
        name=user.getName();
        username = user.getUsername();
        password=user.getPassword();
        phoneNumber = user.getPhoneNumber();
        authorities= Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        topAuthority = user.getTopRole();
        isAccountNonLocked = user.isAccountNonLocked();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return name;
    }

    public String getTopAuthority() {
        return topAuthority;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User getUser() {
        return user;
    }
}
