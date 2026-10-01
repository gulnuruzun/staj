package org.example.controller;

import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus; // <-- Bu import şarttır
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService = new ProductService();

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        List<Product> products = productService.filterProducts(name, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product createdProduct = productService.addProduct(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
}