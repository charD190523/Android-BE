package com.example.bhd.repository;

import com.example.bhd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String username);
    Optional<User> findById(Integer id);
    List<User> findByRole(String username);
}
