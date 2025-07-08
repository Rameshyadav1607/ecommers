package com.ecommers.repository;

import com.ecommers.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByMobileNumberOrEmail(String mobileNumber, String email);
}