package org.example.controller;

import org.example.model.ShoppingCart;
import org.example.repository.UserRepository;
import org.springframework.http.HttpStatus;
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


    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestParam int userId, @RequestBody ShoppingCart cart) {

        boolean isUserExists = UserRepository.getUserList().stream()
                .anyMatch(user -> user.getId() == userId);

        if (!isUserExists) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Hata: Kayıtlı olmayan kullanıcı ID'si ile işlem yapılamaz!");
        }

        cart.setId(idCounter++);
        cart.setUserId(userId);
        cartList.add(cart);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ShoppingCart>> getCartByUserId(@RequestParam int userId) {
        List<ShoppingCart> userCarts = cartList.stream()
                .filter(cart -> cart.getUserId() == userId)
                .collect(Collectors.toList());

        if (userCarts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userCarts);
    }
}