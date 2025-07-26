package com.ecommers.service;

import com.ecommers.domain.OrderRequestDomain;
import com.ecommers.domain.RequestItemsDomain;
import com.ecommers.model.Customer;
import com.ecommers.model.DeliveryAddress;
import com.ecommers.model.OrderItems;
import com.ecommers.model.Orders;
import com.ecommers.model.Product;
import com.ecommers.repository.CustomerRepository;
import com.ecommers.repository.DeliveryAddressRepository;
import com.ecommers.repository.OrderItemsRepository;
import com.ecommers.repository.OrdersRepository;
import com.ecommers.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Service
public class OrderService {

    private CustomerRepository customerRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final ProductRepository productRepository;
    private final OrdersRepository ordersRepository;
    private final OrderItemsRepository orderItemsRepository;

//    public OrderService(DeliveryAddressRepository deliveryAddressRepository, ProductRepository productRepository, OrdersRepository ordersRepository, OrderItemsRepository orderItemsRepository) {
//        this.deliveryAddressRepository = deliveryAddressRepository;
//        this.productRepository = productRepository;
//        this.ordersRepository = ordersRepository;
//        this.orderItemsRepository = orderItemsRepository;
//    }

    public OrderService(CustomerRepository customerRepository,
                        DeliveryAddressRepository deliveryAddressRepository,
                        ProductRepository productRepository,
                        OrdersRepository ordersRepository,
                        OrderItemsRepository orderItemsRepository) {
        this.customerRepository = customerRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.productRepository = productRepository;
        this.ordersRepository = ordersRepository;
        this.orderItemsRepository = orderItemsRepository;
    }

    public String createOrder(OrderRequestDomain orderRequestDomain) {
        Customer customer = customerRepository
                .findByCustomerId(orderRequestDomain.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        DeliveryAddress deliveryAddress = deliveryAddressRepository.findByCustomerCustomerIdAndDeliveryAddressId(orderRequestDomain.getCustomerId(), orderRequestDomain.getDeliveryAddressId());

        Orders order = new Orders();
        order.setCustomer(customer);
        order.setDeliveryAddress(deliveryAddress);
        order.setOrderDate(Date.valueOf(LocalDate.now()));
        order.setOrderUpdateDate(null);
        BigDecimal totalOrdersAmount=BigDecimal.valueOf(0);
        Set<OrderItems> orderItemsSet = new HashSet<>();

        for(RequestItemsDomain items :orderRequestDomain.getRequestItemsDomainList()){

            Product product = productRepository.findById(items.getProductId()).orElseThrow(()-> new RuntimeException("product not found"));

            BigDecimal productPrice=BigDecimal.valueOf(product.getPrice());
            Integer quantity=items.getQuantity();
            BigDecimal totalProductPrice=productPrice.multiply(BigDecimal.valueOf(quantity));
            OrderItems orderItems=new OrderItems();
            orderItems.setPrice(productPrice);
            orderItems.setTotalAmount(totalProductPrice);
            orderItems.setQuantity(quantity);
            orderItems.setOrders(order);
            orderItems.setProduct(product);

            orderItemsSet.add(orderItems);

            totalOrdersAmount=totalProductPrice;

        }

        order.setTotalAmount(totalOrdersAmount);
        order.setOrderItemses(orderItemsSet);

        Orders savedOrder = ordersRepository.save(order);

        for (OrderItems allitems:orderItemsSet){
            allitems.setOrders(savedOrder);
            orderItemsRepository.save(allitems);
        }

        return  "order placed";
    }
}
