package com.uncledavecode.inventory.repository;

import com.uncledavecode.inventory.model.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findBySku(String sku);

//    List<Optional<Inventory>> findBySkuIn(List<String> skus);

    List<Inventory> findBySkuIn(List<String> skus);

}
