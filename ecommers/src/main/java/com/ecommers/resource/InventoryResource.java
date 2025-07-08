package com.ecommers.resource;

import com.ecommers.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryResource {
    @Autowired
    private InventoryService inventoryService;
}
