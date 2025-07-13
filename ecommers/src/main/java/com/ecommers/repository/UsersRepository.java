package com.ecommers.repository;

import com.ecommers.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByMobileNumberOrEmail(String mobileNumber, String email);

    Optional<Users> findByUsername(String username);

    @Query("Select u from Users u where u.email=: input or u.mobileNumber=:input")
    Optional<Users> findByMobileNumberOrEmailId(@Param("input") String emailIdOrPhoneNumner);
}