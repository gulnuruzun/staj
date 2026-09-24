package org.example;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }
    public void addProduct(Product product) {
        items.add(product);
        System.out.println(product.getName() + " sepete eklendi.");
    }

    public void removeProduct(String productName) {
        Product productToRemove = null;
        for (Product p : items) {
            if (p.getName().equalsIgnoreCase(productName)) {
                productToRemove = p;
                break;
            }
        }

        if (productToRemove != null) {
            items.remove(productToRemove);
            System.out.println(productName + " sepetten çıkarıldı.");
        } else {
            System.out.println(productName + " sepette bulunamadı!");
        }
    }

    public void listProducts() {
        if (items.isEmpty()) {
            System.out.println("Sepetiniz boş.");
        } else {
            System.out.println("--- Sepetteki Ürünler ---");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i));
            }
        }
    }

    public void checkProduct(String productName) {
        boolean found = false;
        for (Product p : items) {
            if (p.getName().equalsIgnoreCase(productName)) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Evet, '" + productName + "' sepette var.");
        } else {
            System.out.println("Hayır, '" + productName + "' sepette yok.");
        }
    }
}