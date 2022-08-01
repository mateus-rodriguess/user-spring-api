package com.api.user.services;

import com.api.user.models.UserModel;
import com.api.user.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    @Transactional
    public UserModel save(UserModel userModel){
        //userModel.setPassword(bCryptPasswordEncoder().encode(userModel.getPassword()));
        return userRepository.save(userModel);
    }
    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
    public Optional<UserModel> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public boolean existByEmail(String email){
        return  userRepository.existsByEmail(email);
    }
    public Page<UserModel> findAll(Pageable pageable){
        return userRepository.findAll(pageable);
    }
    public Optional<UserModel> findById(UUID id){
        return userRepository.findById(id);
    }
    @Transactional
    public void delete(UserModel userModel){
        userRepository.delete(userModel);
    }

}
