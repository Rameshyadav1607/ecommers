package com.ecommers.service;

import com.ecommers.domain.CustomerDomain;
import com.ecommers.domain.CustomerInfoDomain;
import com.ecommers.model.Customer;
import com.ecommers.model.Users;
import com.ecommers.repository.CustomerRepository;
import com.ecommers.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
   @Autowired
   private CustomerRepository customerRepository;
    @Autowired
    private UsersRepository usersRepository;

    public String createAccount(CustomerDomain customerDomain){

        Users user = usersRepository.findByMobileNumberOrEmail(customerDomain.getUserdomain().getMobileNumber(), customerDomain.getUserdomain().getEmail());
        if(user == null) {
            Users users = new Users();
            users.setUsername(customerDomain.getUserdomain().getUsername());
            users.setEmail(customerDomain.getUserdomain().getEmail());
            users.setUserRole("customer");
            users.setActive(true);
            users.setMobileNumber(customerDomain.getUserdomain().getMobileNumber());

            Customer customer = new Customer();
            customer.setFirstname(customerDomain.getFirstname());
            customer.setLastname(customerDomain.getLastname());
            customer.setUsers(users);

            customerRepository.save(customer);

            return "user created ";
        }
        else{
            return "already you have account";
        }

   }

    public List<CustomerInfoDomain> getAllCustomersDetails() {

       List<CustomerInfoDomain> customer=customerRepository.getAllCustomersDetails();


        return  customer;
    }
}
