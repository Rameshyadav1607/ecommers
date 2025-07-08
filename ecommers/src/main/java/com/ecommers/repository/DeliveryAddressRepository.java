package com.ecommers.repository;

import com.ecommers.model.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Integer> {

    DeliveryAddress findByCustomerCustomerIdAndDeliveryAddressId(Integer customerId, Integer deliveryAddressId);
}