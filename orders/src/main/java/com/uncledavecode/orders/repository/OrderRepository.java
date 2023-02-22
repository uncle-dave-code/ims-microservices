package com.uncledavecode.orders.repository;

import com.uncledavecode.orders.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
