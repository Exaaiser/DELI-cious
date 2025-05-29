package com.pluralsight;

import com.pluralsight.builder.ChipBuilder;
import com.pluralsight.builder.DrinkBuilder;
import com.pluralsight.builder.SandwichBuilder;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private final OrderManager orderManager = new OrderManager();
    private Order currentOrder; // Aktif sipariş

    public void start() {
        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    startNewOrder();
                    break;
                case "2":
                    viewPreviousOrders();
                    break;
                case "0":
                    System.out.println("Exiting... Goodbye!"); // Çıkılıyor... Hoşça kal!
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input, please try again."); // Geçersiz giriş, tekrar deneyin.
            }
        }
        scanner.close();
    }

    private void printMainMenu() {
        System.out.println("\n=== DELI-cious ===");
        System.out.println("1) New Order"); // Yeni Sipariş
        System.out.println("2) View Previous Orders"); // Önceki Siparişleri Görüntüle
        System.out.println("0) Exit"); // Çıkış
        System.out.print("Your choice: "); // Seçiminiz:
    }

    private void startNewOrder() {
        System.out.println("\n--- Starting New Order ---"); // Yeni Sipariş Başlatılıyor
        currentOrder = new Order(); // Yeni bir sipariş başlat
        boolean ordering = true;

        while (ordering) {
            printOrderMenu();
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    Sandwich sandwich = SandwichBuilder.build(scanner);
                    if (sandwich != null) {
                        currentOrder.addItem(sandwich);
                        System.out.println("Sandwich added to order."); // Sandviç siparişe eklendi.
                    } else {
                        System.out.println("Sandwich addition cancelled."); // Sandviç ekleme iptal edildi.
                    }
                    break;
                case "2":
                    Drink drink = DrinkBuilder.build(scanner);
                    if (drink != null) {
                        currentOrder.addItem(drink);
                        System.out.println("Drink added to order."); // İçecek siparişe eklendi.
                    } else {
                        System.out.println("Drink addition cancelled."); // İçecek ekleme iptal edildi.
                    }
                    break;
                case "3":
                    Chip chip = ChipBuilder.build(scanner);
                    if (chip != null) {
                        currentOrder.addItem(chip);
                        System.out.println("Chips added to order."); // Cips siparişe eklendi.
                    } else {
                        System.out.println("Chips addition cancelled."); // Cips ekleme iptal edildi.
                    }
                    break;
                case "4":
                    checkout();
                    ordering = false; // Sipariş bitirildi, döngüden çık
                    break;
                case "0":
                    cancelOrder();
                    ordering = false; // Sipariş iptal edildi, döngüden çık
                    break;
                default:
                    System.out.println("Invalid choice, please try again."); // Geçersiz seçim, tekrar deneyin.
            }
        }
    }

    private void printOrderMenu() {
        System.out.println("\n--- Order Menu ---"); // Sipariş Menüsü
        System.out.println("1) Add Sandwich"); // Sandviç Ekle
        System.out.println("2) Add Drink"); // İçecek Ekle
        System.out.println("3) Add Chips"); // Cips Ekle
        System.out.println("4) Checkout"); // Siparişi Bitir (Checkout)
        System.out.println("0) Cancel Order"); // Siparişi İptal Et
        System.out.print("Your choice: "); // Seçiminiz:
    }

    private void checkout() {
        if (currentOrder.getItems().isEmpty()) {
            System.out.println("\nYour order is empty, cannot checkout."); // Siparişiniz boş, ödeme yapılamaz.
            return;
        }

        currentOrder.displayOrder(); // Sipariş detaylarını ve toplam fiyatı göster

        while (true) {
            System.out.println("\n--- Checkout Screen ---"); // Ödeme Ekranı
            System.out.println("1) Confirm (Save Receipt and Return to Home)"); // Onayla (Makbuzu Kaydet ve Ana Menüye Dön)
            System.out.println("0) Cancel (Delete Order and Return to Home)"); // İptal Et (Siparişi Sil ve Ana Menüye Dön)
            System.out.print("Your choice: "); // Seçiminiz:
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    orderManager.saveOrder(currentOrder); // Makbuzu kaydet
                    currentOrder.clear(); // Siparişi temizle
                    System.out.println("Your order has been successfully completed. Returning to home menu..."); // Siparişiniz başarıyla tamamlandı. Ana menüye dönülüyor...
                    return; // checkout'tan çık
                case "0":
                    cancelOrder(); // Siparişi iptal et
                    return; // checkout'tan çık
                default:
                    System.out.println("Invalid choice, please try again."); // Geçersiz seçim, tekrar deneyin.
            }
        }
    }

    private void cancelOrder() {
        currentOrder.clear(); // Siparişi temizle
        System.out.println("\nOrder cancelled. Returning to home menu..."); // Sipariş iptal edildi. Ana menüye dönülüyor...
    }

    private void viewPreviousOrders() {
        System.out.println("\n--- View Previous Orders ---"); // Önceki Siparişleri Görüntüle
        List<String> orders = orderManager.loadPreviousOrders();

        if (orders.isEmpty()) {
            System.out.println("No previous orders to display."); // Gösterilecek geçmiş sipariş bulunamadı.
        } else {
            for (String orderContent : orders) {
                System.out.println(orderContent);
            }
        }
    }
}