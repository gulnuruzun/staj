package org.example.service;

import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.Product;
import org.example.model.ShoppingCart;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static final List<Order> orderList = new ArrayList<>();

    public Order checkout(int userId, List<ShoppingCart> userCart, boolean simulatePaymentFailure) {
        boolean userExists = UserRepository.getUserList().stream().anyMatch(u -> u.getId() == userId);
        if (!userExists) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Kayıtsız kullanıcı sipariş veremez!");
        }

        if (userCart == null || userCart.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sepetiniz boş, sipariş oluşturulamaz!");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        double grandTotal = 0.0;

        for (ShoppingCart cartItem : userCart) {
            Product product = ProductRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sepetteki ürün bulunamadı!"));

            OrderItem item = new OrderItem(
                    product.getId(),
                    product.getName(),
                    cartItem.getQuantity(),
                    product.getPrice()
            );

            orderItems.add(item);
            grandTotal += item.getTotalPrice();
        }

        if (simulatePaymentFailure) {
            throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED, "Ödeme başarısız oldu, kart limiti yetersiz!");
        }

        Order newOrder = new Order(orderList.size() + 1, userId, orderItems, grandTotal, "SUCCESS");
        orderList.add(newOrder);
        return newOrder;
    }

    public List<Order> getOrdersByUser(int userId) {
        return orderList.stream().filter(o -> o.getUserId() == userId).toList();
    }
}