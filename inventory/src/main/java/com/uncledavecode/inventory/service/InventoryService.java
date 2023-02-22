package com.uncledavecode.inventory.service;

import com.uncledavecode.inventory.model.dtos.BaseResponses;
import com.uncledavecode.inventory.model.dtos.OrderItemRequest;
import com.uncledavecode.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String sku) {
        var inventory = inventoryRepository.findBySku(sku);
        return inventory.filter(value -> value.getQuantity() > 0).isPresent();
    }

    public BaseResponses areInStock(List<OrderItemRequest> orderItems) {
        var result = new BaseResponses();
        var errorMessages = new ArrayList<String>();

        List<String> skus = orderItems.stream().map(OrderItemRequest::getSku).toList();

        var inventoryList = inventoryRepository.findBySkuIn(skus);

        orderItems.forEach(orderItem -> {
            var inventory = inventoryList.stream().filter(value -> value.getSku().equals(orderItem.getSku())).findFirst();
            if (inventory.isEmpty()) {
                errorMessages.add("Product with sku " + orderItem.getSku() + " does not exist");
            } else if (inventory.get().getQuantity() < orderItem.getQuantity()) {
                errorMessages.add("Product with sku " + orderItem.getSku() + " has insufficient quantity");
            }
        });

        if (errorMessages.size() > 0) {
            result.setErrorMessages(errorMessages.toArray(new String[0]));
        }

        return result;
    }
}
