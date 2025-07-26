package com.ecommers.service;

import com.ecommers.domain.OrderRequestDomain;
import com.ecommers.domain.RequestItemsDomain;
import com.ecommers.model.Customer;
import com.ecommers.model.DeliveryAddress;
import com.ecommers.model.Orders;
import com.ecommers.model.Product;
import com.ecommers.repository.CustomerRepository;
import com.ecommers.repository.DeliveryAddressRepository;
import com.ecommers.repository.OrderItemsRepository;
import com.ecommers.repository.OrdersRepository;
import com.ecommers.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private DeliveryAddressRepository deliveryAddressRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private OrderItemsRepository orderItemsRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testCreateOrderSuccess() {

        Integer customerId = 5;
        Integer productId = 1;
        Integer addressId = 1;

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        DeliveryAddress address = new DeliveryAddress();
        address.setDeliveryAddressId(addressId);
        Product product = new Product();
        product.setProductId(productId); product.setPrice(100.0);

        RequestItemsDomain item = new RequestItemsDomain();
        item.setProductId(productId);
        item.setQuantity(2);
        OrderRequestDomain request = new OrderRequestDomain();
        request.setCustomerId(customerId);
        request.setDeliveryAddressId(addressId);
        request.setRequestItemsDomainList(List.of(item));

        Orders order = new Orders();

        when(customerRepository.findByCustomerId(customerId)).thenReturn(Optional.of(customer));
        when(deliveryAddressRepository.findByCustomerCustomerIdAndDeliveryAddressId(customerId, addressId)).thenReturn(address);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));


        String result = orderService.createOrder(request);


        assertEquals("order placed", result);
        verify(customerRepository).findByCustomerId(customerId);
        verify(deliveryAddressRepository).findByCustomerCustomerIdAndDeliveryAddressId(customerId, addressId);
        verify(productRepository).findById(productId);

    }

    @Test
    public void testCreateOrderCustomerNotFound() {
        Integer customerId = 10;

        OrderRequestDomain request = new OrderRequestDomain();
        request.setCustomerId(customerId);

        when(customerRepository.findByCustomerId(customerId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderService.createOrder(request);
        });

        assertEquals("Customer not found", exception.getMessage());
    }
}

