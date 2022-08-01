package com.api.user.repositories;

import com.api.user.models.UserModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

     boolean existsByUsername(String username);
     public Optional<UserModel> findByUsername(String Username);
     boolean existsByEmail(String email);
}
