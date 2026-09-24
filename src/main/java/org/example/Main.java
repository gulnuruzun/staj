package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Sepet Yönetim Sistemi ---");
            System.out.println("1. Ürün Ekle");
            System.out.println("2. Ürün Çıkar");
            System.out.println("3. Ürünleri Listele");
            System.out.println("4. Ürün Sorgula (Var mı?)");
            System.out.println("5. Çıkış");
            System.out.print("Seçiminiz: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Ürün adı: ");
                    String name = scanner.nextLine();
                    System.out.print("Ürün fiyatı: ");
                    double price = scanner.nextDouble();
                    cart.addProduct(new Product(name, price));
                    break;
                case 2:
                    System.out.print("Çıkarılacak ürün adı: ");
                    String removeName = scanner.nextLine();
                    cart.removeProduct(removeName);
                    break;
                case 3:
                    cart.listProducts();
                    break;
                case 4:
                    System.out.print("Sorgulanacak ürün adı: ");
                    String searchName = scanner.nextLine();
                    cart.checkProduct(searchName);
                    break;
                case 5:
                    System.out.println("Programdan çıkılıyor...");
                    break;
                default:
                    System.out.println("Geçersiz seçim, tekrar deneyin.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
