package com.uncledavecode.inventory.controller;

import com.uncledavecode.inventory.model.dtos.BaseResponses;
import com.uncledavecode.inventory.model.dtos.OrderItemRequest;
import com.uncledavecode.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku") String sku) {
        return inventoryService.isInStock(sku);
    }

    @PostMapping("/in-stock")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponses areInStock(@RequestBody List<OrderItemRequest> orderItems) {
        //simulateSlowService();
        return inventoryService.areInStock(orderItems);
    }

    private void simulateSlowService() {
        try {
            log.info("Simulating slow service...");
            Thread.sleep(10000L);
            log.info("Simulating slow service...done");
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
