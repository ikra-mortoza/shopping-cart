package com.ikramortoza.shoppingcart.repository;

import com.ikramortoza.shoppingcart.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
