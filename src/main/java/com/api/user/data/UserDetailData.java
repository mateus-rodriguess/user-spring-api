package com.api.user.data;

import com.api.user.models.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class UserDetailData implements UserDetails {
    private final Optional<UserModel> userModel;

    public UserDetailData(Optional<UserModel> userModel) {
        this.userModel = userModel;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }
    @Override
    public String getPassword() {
        return userModel.orElse(new UserModel()).getPassword();
    }

    @Override
    public String getUsername() {
        return userModel.orElse(new UserModel()).getUsername();
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
