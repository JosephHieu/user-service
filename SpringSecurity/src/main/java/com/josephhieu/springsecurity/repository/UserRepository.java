package com.josephhieu.springsecurity.repository;

import com.josephhieu.springsecurity.entity.User_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User_Entity, String> {

    boolean existsByUsername(String username);

    Optional<User_Entity> findByUsername(String username);
}
