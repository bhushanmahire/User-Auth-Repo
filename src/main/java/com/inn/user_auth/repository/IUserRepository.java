package com.inn.user_auth.repository;

import com.inn.user_auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
  User findByUserName(String userName);
  User findByEmail(String email);
}
