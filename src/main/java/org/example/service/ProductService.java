package org.example.service;

import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ProductService {

    public Product addProduct(int id, String name, double price, int stock) {
        boolean exists = ProductRepository.getAllProducts().stream()
                .anyMatch(p -> p.getId() == id);
        if (exists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bu ID'ye sahip ürün zaten mevcut!");
        }
        if (price < 0 || stock < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fiyat veya stok negatif olamaz!");
        }

        Product product = new Product(id, name, price, stock);
        ProductRepository.getAllProducts().add(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return ProductRepository.getAllProducts();
    }

    public List<Product> filterProducts(String name, Double minPrice, Double maxPrice) {
        return ProductRepository.getAllProducts().stream()
                .filter(p -> name == null || p.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(p -> minPrice == null || p.getPrice() >= minPrice)
                .filter(p -> maxPrice == null || p.getPrice() <= maxPrice)
                .toList();
    }
}