package com.aramat.wish2eat.repositories;

import com.aramat.wish2eat.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
