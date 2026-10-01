package org.example.service;

import org.example.model.ShoppingCart;
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCartService {
    private static final List<ShoppingCart> cartList = new ArrayList<>();

    public ShoppingCart addToCart(int userId, int productId, int quantity) {
        // 1. Kayıtsız kullanıcı kontrolü -> 403 Forbidden
        boolean userExists = UserRepository.getUserList().stream().anyMatch(u -> u.getId() == userId);
        if (!userExists) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Kayıtsız kullanıcı işlem yapamaz!");
        }

        // 2. Geçerli adet kontrolü
        if (quantity <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sepete en az 1 adet ürün ekleyebilirsiniz!");
        }

        // 3. Ürün varlık kontrolü -> 404 Not Found
        Product product = ProductRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID'si verilen ürün bulunamadı!"));

        // 4. Stok yeterlilik kontrolü
        if (product.getStock() < quantity) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Yetersiz stok! Mevcut stok: " + product.getStock());
        }

        // 5. Ürün sepete eklendiği anda stoktan düşür
        product.setStock(product.getStock() - quantity);

        // 6. Sepette aynı ürün varsa miktarını artır, yoksa yeni kayıt oluştur
        Optional<ShoppingCart> existingCartItem = cartList.stream()
                .filter(c -> c.getUserId() == userId && c.getProductId() == productId)
                .findFirst();

        if (existingCartItem.isPresent()) {
            ShoppingCart cart = existingCartItem.get();
            cart.setQuantity(cart.getQuantity() + quantity);
            return cart;
        } else {
            ShoppingCart newCart = new ShoppingCart(cartList.size() + 1, userId, productId, quantity);
            cartList.add(newCart);
            return newCart;
        }
    }

    public List<ShoppingCart> getCartByUser(int userId) {
        boolean userExists = UserRepository.getUserList().stream().anyMatch(u -> u.getId() == userId);
        if (!userExists) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Kayıtsız kullanıcı sepetini görüntüleyemez!");
        }

        return cartList.stream().filter(c -> c.getUserId() == userId).toList();
    }

    public void clearCartForUser(int userId) {
        cartList.removeIf(c -> c.getUserId() == userId);
    }
}