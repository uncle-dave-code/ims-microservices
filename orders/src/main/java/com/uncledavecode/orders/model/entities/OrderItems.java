package com.uncledavecode.orders.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    private BigDecimal price;
    private Long quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
