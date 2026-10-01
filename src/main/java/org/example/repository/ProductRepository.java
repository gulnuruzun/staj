package org.example.repository;

import org.example.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private static final List<Product> productList = new ArrayList<>();

    static {
        // ID, İsim, Fiyat, Stok
        productList.add(new Product(1, "Laptop", 25000.0, 10));
        productList.add(new Product(2, "Kulaklık", 1500.0, 25));
        productList.add(new Product(3, "Akıllı Saat", 5000.0, 15));
    }

    public static List<Product> getAllProducts() {
        return productList;
    }

    public static Optional<Product> findById(int id) {
        return productList.stream().filter(p -> p.getId() == id).findFirst();
    }
}