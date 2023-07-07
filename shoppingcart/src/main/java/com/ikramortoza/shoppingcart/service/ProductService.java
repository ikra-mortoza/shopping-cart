package com.ikramortoza.shoppingcart.service;
import com.ikramortoza.shoppingcart.models.Product;
import com.ikramortoza.shoppingcart.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProduct(Integer id) {
        return productRepository.findById(id).get();
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

}
