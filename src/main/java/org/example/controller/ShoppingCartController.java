package org.example.controller;

import org.example.model.ShoppingCart;
import org.example.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    private final ShoppingCartService cartService = new ShoppingCartService();

    @PostMapping("/add")
    public ResponseEntity<ShoppingCart> addToCart(
            @RequestParam int userId,
            @RequestParam int productId,
            @RequestParam int quantity) {

        ShoppingCart cartItem = cartService.addToCart(userId, productId, quantity);
        return ResponseEntity.ok(cartItem);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ShoppingCart>> getCartByUser(@PathVariable int userId) {
        List<ShoppingCart> cartItems = cartService.getCartByUser(userId);
        return ResponseEntity.ok(cartItems);
    }
}