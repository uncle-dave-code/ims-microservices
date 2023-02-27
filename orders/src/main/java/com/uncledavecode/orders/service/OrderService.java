package com.uncledavecode.orders.service;

import com.uncledavecode.orders.model.dtos.BaseResponses;
import com.uncledavecode.orders.model.dtos.OrderItemRequest;
import com.uncledavecode.orders.model.dtos.OrderRequest;
import com.uncledavecode.orders.model.entities.Order;
import com.uncledavecode.orders.model.entities.OrderItems;
import com.uncledavecode.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
//    private final WebClient webClient;
    private final WebClient.Builder webClientBuilder;
    public String placeOrder(OrderRequest orderRequest) {

        //Call inventory service and place order if product is in stock
        BaseResponses result = webClientBuilder.build().post()
                .uri("http://inventory-service/api/inventory/in-stock")
                .bodyValue(orderRequest.getOrderItems())
                .retrieve()
                .bodyToMono(BaseResponses.class)
                .block();
        if (result != null && !result.hasErrors()) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderItems(orderRequest.getOrderItems().stream()
                    .map(orderItemRequest -> mapOrderItemRequestToOrderItem(orderItemRequest, order))
                    .toList());

            orderRepository.save(order);
            return "Order placed successfully";
        } else {
            throw new IllegalArgumentException("Some of the products are not in stock");
        }
    }

    private OrderItems mapOrderItemRequestToOrderItem(OrderItemRequest orderItemRequest, Order order) {
        return OrderItems.builder()
                .id(orderItemRequest.getId())
                .sku(orderItemRequest.getSku())
                .price(orderItemRequest.getPrice())
                .quantity(orderItemRequest.getQuantity())
                .order(order)
                .build();
    }
}
