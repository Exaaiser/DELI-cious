package com.pluralsight;

import com.pluralsight.toppings.SauceTopping;
import com.pluralsight.toppings.Topping;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Item> items = new ArrayList<>(); // Creating Item lisr

    //Adding one ''AddItem'' method for everythings
    public void addItem(Item item) {
        if (item != null) { // If there is a no item, we check that
            items.add(item);
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void displayOrder() {
        if (items.isEmpty()) {
            System.out.println("Order is Empty.");
            return;
        }

        System.out.println("\n Order Ingredients :");
        for (Item item : items) {
            // Detailed interface for Sandwiches
            if (item instanceof Sandwich) {
                System.out.println(((Sandwich) item).toString()); // Sandwich'in kendi toString'i kullanılacak
            } else {
                System.out.println("- " + item.getName() + ": $" + String.format("%.2f", item.getPrice()));
            }
        }
        System.out.println("\n Total: $" + String.format("%.2f", getTotalPrice()));
    }

    public void printReceipt() {
        // Makbuz dosyasının adını tarih ve saate göre oluşturma
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String filename = "receipts/" + now.format(formatter) + ".txt";

        // 'receipts' klasörünün var olduğundan emin olun
        java.io.File receiptsDir = new java.io.File("receipts");
        if (!receiptsDir.exists()) {
            receiptsDir.mkdirs(); // Klasörü oluştur
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("----- DELI-cious Order Receipts -----\n");
            writer.write("Order Date: " + now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) + "\n");
            writer.write("--------------------------------------\n");
            writer.write("Products:\n");

            // Iterate over each item in the 'items' list.
            for (Item item : items) {
                // Check if the current item is an instance of Sandwich.
                if (item instanceof Sandwich) {
                    Sandwich s = (Sandwich) item;
                    // Write the sandwich's name, size, bread type
                    writer.write(s.getName() + " (" + s.getSize() + "\", " + s.getBreadType() + ")");
                    // Check if the sandwich is toasted.
                    if (s.isToasted()) {
                        // If toasted, show "(Toasted)" to the line.
                        writer.write(" (Toasted)");
                    }
                    // decimal output.
                    // Sandviçin fiyatını, iki ondalık basamakla formatlayarak, ardından yeni bir satır karakteriyle birlikte yaz.
                    writer.write(" - $" + String.format("%.2f", s.getPrice()) + "\n");
                    // Check if the sandwich has any toppings sauces or side items.
                    if (!s.getToppings().isEmpty() || !s.getSauces().isEmpty() || !s.getSides().isEmpty()) {
                        // If we have anything add Toppings
                        writer.write("  Toppings: ");
                        boolean first = true;
                        for (Topping t : s.getToppings()) {
                            // If it's not the first topping, write a comma and a spac
                            if (!first) writer.write(", ");
                            // Write the string representation of the toppin
                            // Topping'in string temsilini yaz.
                            writer.write(t.toString());
                            first = false;
                            // After first item change the boolen variable
                        }
                        // Iterate over each sauce topping.
                        for (SauceTopping st : s.getSauces()) {
                            // If it's not the first topping/sauce write a comma and a space
                            if (!first) writer.write(", ");
                            // Write the string representation of the sauce topping
                            writer.write(st.toString());
                            first = false;
                        }
                        // Iterate over each side item.
                        for (SideItem si : s.getSides()) {
                            // If it's not the first topping/sauce/side item write a comma and a space.
                            if (!first) writer.write(", ");
                            // Get name of Site Item and write Side Item and Side Item's name
                            writer.write(si.getName() + " (Side Item)");
                            first = false;
                        }
                        // After listing all toppings/sauces/sides, write a newline
                        writer.write("\n");
                    }
                } else {
                    // If the item is not a Sandwich write its name and price
                    writer.write(item.getName() + ": $" + String.format("%.2f", item.getPrice()) + "\n");
                }
            }

            writer.write("--------------------------------------\n");
            writer.write("Total: $" + String.format("%.2f", getTotalPrice()) + "\n");
            writer.write("--------------------------------------\n");
            System.out.println("Receipt '" + filename + "' saved.");

        } catch (IOException e) {
            System.err.println("Invalid error happened when file writing: " + e.getMessage());
        }
    }

    public void clear() {
        items.clear();
    }
}