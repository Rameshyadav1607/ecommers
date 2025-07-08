package com.ecommers.resource;

import com.ecommers.domain.DeliveryAddressDomain;
import com.ecommers.service.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deliveryaddress")
public class DeliveryAddressResource {
    @Autowired
    private DeliveryAddressService deliveryAddressService;
    @PostMapping("/save")
    public DeliveryAddressDomain saveDeliveryAddress(@RequestBody DeliveryAddressDomain deliveryAddressDomain){
        return deliveryAddressService.saveDeliveryAddress(deliveryAddressDomain);
    }
    @PutMapping("/update/{customerId}/{deliveryAddressId}")
    public String updateDeliveryAddress(@PathVariable Integer customerId,@PathVariable Integer deliveryAddressId,@RequestBody DeliveryAddressDomain deliveryAddressDomain){
        return deliveryAddressService.updateDeliveryAddress(customerId,deliveryAddressId,deliveryAddressDomain);
    }
    @DeleteMapping("/delete/{customerId}/{deliveryAddressId}")
    public String deleteDeliveryAddress(@PathVariable Integer customerId,@PathVariable Integer deliveryAddressId){
       return   deliveryAddressService.deleteDeliveryAddress(customerId,deliveryAddressId);
    }


}
