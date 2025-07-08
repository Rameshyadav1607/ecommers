package com.ecommers.resource;
import com.ecommers.domain.OrderRequestDomain;
import com.ecommers.model.Customer;
import com.ecommers.repository.CustomerRepository;
import com.ecommers.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/order")
public class OrdersResource {
   @Autowired
   private OrderService orderService;

    @PostMapping("/create")
    public String createOrder(@RequestBody OrderRequestDomain orderRequestDomain){
     return  orderService.createOrder(orderRequestDomain);
    }
}
