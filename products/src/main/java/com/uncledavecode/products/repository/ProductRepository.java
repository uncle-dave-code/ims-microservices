package com.uncledavecode.products.repository;

import com.uncledavecode.products.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
