package org.example.controller;

import org.example.model.Order;
import org.example.model.ShoppingCart;
import org.example.service.OrderService;
import org.example.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService = new OrderService();
    private final ShoppingCartService cartService = new ShoppingCartService();

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<Order> checkout(
            @PathVariable int userId,
            @RequestParam(defaultValue = "false") boolean simulatePaymentFailure) {

        List<ShoppingCart> userCart = cartService.getCartByUser(userId);

        Order order = orderService.checkout(userId, userCart, simulatePaymentFailure);

        cartService.clearCartForUser(userId);

        return ResponseEntity.ok(order);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable int userId) {
        List<Order> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }
}