package com.josephhieu.springsecurity.repository;

import com.josephhieu.springsecurity.entity.User_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User_Entity, String> {

    boolean existsByUserName(String username);
}
