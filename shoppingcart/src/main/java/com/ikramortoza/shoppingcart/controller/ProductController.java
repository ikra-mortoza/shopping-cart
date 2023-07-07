package com.ikramortoza.shoppingcart.controller;
import com.ikramortoza.shoppingcart.models.Product;
import com.ikramortoza.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> getProducts() {
        return productService.listAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id) {
        try {
            Product product = productService.getProduct(id);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void add(@RequestBody Product product) {
        productService.saveProduct(product);
    }

    @PutMapping("/add/{id}")
    public ResponseEntity<?> addToCart(@PathVariable Integer id) {
        try {
            Product product = productService.getProduct(id);
            product.setQuantity(product.getQuantity() + 1);
            productService.saveProduct(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/remove/{id}")
    public ResponseEntity<?> removeFromCart(@PathVariable Integer id) {
        try {
            Product product = productService.getProduct(id);
            if(product.getQuantity() > 0) {
                product.setQuantity(product.getQuantity() - 1);
            }
            productService.saveProduct(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

}
