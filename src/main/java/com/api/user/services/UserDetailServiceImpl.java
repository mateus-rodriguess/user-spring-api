package com.api.user.services;

import com.api.user.data.UserDetailData;
import com.api.user.models.UserModel;
import com.api.user.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = userRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User" + user + "not found");
        }
        return new UserDetailData(user);
    }
}
