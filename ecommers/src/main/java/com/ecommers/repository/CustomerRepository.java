package com.ecommers.repository;

import com.ecommers.domain.CustomerInfoDomain;
import com.ecommers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByCustomerId(Integer customerId);
    @Query("""
        SELECT new com.ecommers.domain.CustomerInfoDomain(
            c.customerId,
            c.firstname,
            c.lastname,
            u.username,
            u.email,
            u.mobileNumber,
            u.active
        )
        FROM Customer c
        JOIN c.users u
    """)
    List<CustomerInfoDomain> getAllCustomersDetails();
}