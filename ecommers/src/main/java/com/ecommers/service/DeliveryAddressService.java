package com.ecommers.service;

import com.ecommers.domain.DeliveryAddressDomain;
import com.ecommers.model.Customer;
import com.ecommers.model.DeliveryAddress;
import com.ecommers.repository.CustomerRepository;
import com.ecommers.repository.DeliveryAddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryAddressService {

    private final CustomerRepository customerRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;

    public DeliveryAddressService(CustomerRepository customerRepository,
                                  DeliveryAddressRepository deliveryAddressRepository) {
        this.customerRepository = customerRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    public DeliveryAddressDomain saveDeliveryAddress(DeliveryAddressDomain deliveryAddressDomain){

        Optional<Customer> customerOptional = customerRepository.findById(deliveryAddressDomain.getCustomerId());
        if(customerOptional.isPresent()){
            DeliveryAddress address=new DeliveryAddress();
            address.setCustomer(customerOptional.get());
            BeanUtils.copyProperties(deliveryAddressDomain,address,"deliveryAddressId");
            DeliveryAddress deliveryAddress = deliveryAddressRepository.save(address);
            DeliveryAddressDomain addressDomain=new DeliveryAddressDomain();
            BeanUtils.copyProperties(deliveryAddress,addressDomain);
            addressDomain.setCustomerId(deliveryAddress.getCustomer().getCustomerId());

            return  addressDomain;
        }
        else{
            return  null;
        }

    }


    public String updateDeliveryAddress(Integer customerId, Integer deliveryAddressId, DeliveryAddressDomain deliveryAddressDomain) {
        DeliveryAddress       deliveryAddress =deliveryAddressRepository.findByCustomerCustomerIdAndDeliveryAddressId(customerId,deliveryAddressId);
        if (deliveryAddress == null) {
            return "Delivery address not found for customer ID " + customerId + " and address ID " + deliveryAddressId;
        }
        deliveryAddress.setAddressLine(deliveryAddressDomain.getAddressLine());
        deliveryAddress.setCity(deliveryAddressDomain.getCity());
        deliveryAddress.setState(deliveryAddressDomain.getState());
        deliveryAddress.setPincode(deliveryAddressDomain.getPincode());
        deliveryAddress.setCountry(deliveryAddressDomain.getCountry());
        deliveryAddress.setType(deliveryAddressDomain.getType());

        deliveryAddressRepository.save(deliveryAddress);
        return "Delivery address updated successfully.";

    }

    public String deleteDeliveryAddress(Integer customerId, Integer deliveryAddressId) {

        DeliveryAddress       deliveryAddress =deliveryAddressRepository.findByCustomerCustomerIdAndDeliveryAddressId(customerId,deliveryAddressId);

        if(deliveryAddress == null){
            return "Delivery address not found for customer ID " + customerId + " and address ID " + deliveryAddressId;
        }
        deliveryAddressRepository.delete(deliveryAddress);
        return  "deleted sucess delivery address";
    }
}
