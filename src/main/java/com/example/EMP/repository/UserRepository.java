package com.example.EMP.repository;

import com.example.EMP.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
