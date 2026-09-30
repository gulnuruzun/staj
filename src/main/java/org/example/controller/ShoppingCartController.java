package org.example.controller;
import org.example.ShoppingCart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {

    private final List<ShoppingCart> cartList = new ArrayList<>();
    private int idCounter = 1;

    // 1. Sepete ürün/işlem eklerken kullanıcı ID'sini zorunlu kılma
    @PostMapping("/add")
    public ShoppingCart addToCart(@RequestParam int userId, @RequestBody ShoppingCart cart) {
        cart.setId(idCounter++);
        cart.setUserId(userId); // ShoppingCart modelinde setUserId olduğunu varsayıyoruz
        cartList.add(cart);
        return cart;
    }

    // 2. Kullanıcı ID'sine göre sepeti listeleme (Bulunamazsa listeleme yapılmaz / 404 döner)
    @GetMapping("/list")
    public ResponseEntity<List<ShoppingCart>> getCartByUserId(@RequestParam int userId) {
        List<ShoppingCart> userCarts = cartList.stream()
                .filter(cart -> cart.getUserId() == userId)
                .collect(Collectors.toList());

        // Kullanıcı ID'si ile eşleşen sepet bulunamazsa listeleme yapılmaz
        if (userCarts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userCarts);
    }
}