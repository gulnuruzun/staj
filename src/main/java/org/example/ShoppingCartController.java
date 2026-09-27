package org.example;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    private List<Product> cart = new ArrayList<>();

    @GetMapping
    public List<Product> getProducts() {
        return cart;
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody Product product) {
        cart.add(product);
        return product.getName() + " sepete eklendi.";
    }

    @DeleteMapping("/remove/{name}")
    public String removeProduct(@PathVariable String name) {
        // Ürün adını büyük/küçük harf duyarlılığı olmadan veya direkt eşleşmeyle silelim
        boolean removed = cart.removeIf(p -> p.getName() != null && p.getName().equalsIgnoreCase(name));
        if (removed) {
            return name + " sepetten çıkarıldı.";
        }
        return name + " sepette bulunamadı!";
    }

    @GetMapping("/check/{name}")
    public String checkProduct(@PathVariable String name) {
        boolean exists = cart.stream().anyMatch(p -> p.getName() != null && p.getName().equalsIgnoreCase(name));
        if (exists) {
            return "Evet, '" + name + "' sepette var.";
        }
        return "Hayır, '" + name + "' sepette yok.";
    }
}