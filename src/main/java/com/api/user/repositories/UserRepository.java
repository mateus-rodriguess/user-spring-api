package com.api.user.repositories;

import com.api.user.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

     boolean existsByUserName(String userName);
}
