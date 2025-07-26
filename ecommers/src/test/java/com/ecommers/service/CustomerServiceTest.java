package com.ecommers.service;

import com.ecommers.domain.CustomerDomain;
import com.ecommers.domain.CustomerInfoDomain;
import com.ecommers.domain.UserDomain;
import com.ecommers.model.Users;
import com.ecommers.repository.CustomerRepository;
import com.ecommers.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void  testCreateAccount_UserAlreadyExists_shouldReturnErrorMessage(){
        UserDomain userDomain=new UserDomain();
        userDomain.setUsername("G.ramesh yadav");
        userDomain.setEmail("rameshgarshana1607@gmail.com");
        userDomain.setMobileNumber("9491139617");
        userDomain.setPassword("chintu");
        userDomain.setConformPassword("chintu");

        CustomerDomain customerDomain=new CustomerDomain();
        customerDomain.setFirstname("Garshana");
        customerDomain.setLastname("ramesh yadav");
        customerDomain.setUserdomain(userDomain);

        when(usersRepository.findByMobileNumberOrEmail(customerDomain.getUserdomain().getMobileNumber()
                ,customerDomain.getUserdomain().getEmail())).thenReturn(new Users());

        String result = customerService.createAccount(customerDomain);
        System.out.println(result);

        assertEquals("already you have account",result);


    }

    @Test
    void testCreateAccount_NewUser_ValidPassword_shouldCreateSuccessfully() {

        UserDomain userDomain=new UserDomain();
        userDomain.setUsername("G.ramesh yadav");
        userDomain.setEmail("rameshgarshana1607@gmail.com");
        userDomain.setMobileNumber("9491139617");
        userDomain.setPassword("chintu");
        userDomain.setConformPassword("chintu");

        CustomerDomain customerDomain=new CustomerDomain();
        customerDomain.setFirstname("Garshana");
        customerDomain.setLastname("ramesh yadav");
        customerDomain.setUserdomain(userDomain);

        when(usersRepository.findByMobileNumberOrEmail(customerDomain.getUserdomain().getMobileNumber()
                ,customerDomain.getUserdomain().getEmail())).thenReturn(null);
        when(passwordEncoder.encode(userDomain.getPassword())).thenReturn("encoded password");

        String result = customerService.createAccount(customerDomain);

        System.out.println(result);
        assertEquals("user created ",result);
    }
    @Test
    void testCreateAccount_PasswordMismatch_shouldReturnErrorMessage() {

        UserDomain userDomain = new UserDomain();
        userDomain.setPassword("abc");
        userDomain.setConformPassword("xyz");
        userDomain.setEmail("new@example.com");
        userDomain.setMobileNumber("7777777777");

        CustomerDomain customerDomain = new CustomerDomain();
        customerDomain.setFirstname("Mismatch");
        customerDomain.setLastname("Password");
        customerDomain.setUserdomain(userDomain);

        when(usersRepository.findByMobileNumberOrEmail("7777777777", "new@example.com")).thenReturn(null);

        String result = customerService.createAccount(customerDomain);
        System.out.println(result);
        assertEquals("incorrect  conform password", result);
    }
    @Test
    void getAllCustomers(){
        CustomerInfoDomain customer1 = new CustomerInfoDomain(
                1, "John", "Doe", "john_doe", "john@example.com", "9999999999", true);
        CustomerInfoDomain customer2 = new CustomerInfoDomain(
                2, "Jane", "Smith", "jane_smith", "jane@example.com", "8888888888", true);

        List<CustomerInfoDomain> mockList = Arrays.asList(customer1, customer2);
         when(customerRepository.getAllCustomersDetails()).thenReturn(mockList);
        List<CustomerInfoDomain> result = customerService.getAllCustomersDetails();
        assertEquals(2,result.size());
        assertEquals("John", result.get(0).getFirstname());
        assertEquals("Jane", result.get(1).getFirstname());

        verify(customerRepository).getAllCustomersDetails();

        for( CustomerInfoDomain customer : mockList){
            System.out.println(customer);
        }

    }

}