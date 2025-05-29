package com.pluralsight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    public OrderManager() {
        // Constructor
    }

    public void saveOrder(Order order) {
        if (order != null) {
            order.printReceipt(); // We calling order classes reciept writer method
        } else {
            System.out.println("No valid order to save."); //  If we dont have anything we ll se this.
        }
    }

    public List<String> loadPreviousOrders() {
        List<String> previousOrders = new ArrayList<>();
        File receiptsDir = new File("receipts"); // Folder of reciepts loaded

        // Klasördeki .txt uzantılı tüm dosyaları al
        // We looking for any txt file in file directinos
        File[] receiptFiles = receiptsDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        if (receiptFiles == null || receiptFiles.length == 0) {
            System.out.println("No previous orders found.");
            return previousOrders;
        }


        for (File file : receiptFiles) {
            // Create a new StringBuilder object for each file.
            // StringBuilder used for efficient  concatenation when building the content of each order (better network)
            // StringBuilder, her bir siparişin içeriğini oluştururken verimli string birleştirme için kullanılır.
            StringBuilder orderContent = new StringBuilder();


            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                // Declare every str'ng var'able
                // Dosyadan okunan her bir satırı tutacak bir String değişkeni tanımla.
                String line;

                // Append a header to the order content including the receipt files name
                // This makes it clear which receipts content is being displayed
                // Sipariş içeriğine, makbuz dosyasının adını içeren bir başlık ekle.
                // Bu, hangi makbuzun içeriğinin görüntülendiğini netleştirir.
                orderContent.append("\n----- Receipt: ").append(file.getName()).append(" -----\n"); // Makbuz başlığı

                // reading one line at a time
                // The loop continues as long as 'reader.readLine()' returns a non-null value (I mean not the end of the file).
                // Döng] reader.readLine() null olmayan bir değer döndürdüğü sürece devam eder (yani dosyanın sonuna ulaşılmadığı sürece).
                while ((line = reader.readLine()) != null) {
                    // Append the read line to the StringBuilder after that add by a newline character
                    // Okunan satırı StringBuilder'a ekle, ardından bir yeni satır karakteri ekle.
                    orderContent.append(line).append("\n");
                }
                orderContent.append("--------------------------------------\n");

                // Convert StringBuilder content to a String and add it to the previousOrders list
                previousOrders.add(orderContent.toString());
            } catch (IOException e) {
                System.err.println("Error reading receipt file: " + file.getName() + " - " + e.getMessage()); // Something going wrong
            }
        }
        return previousOrders;
    }
}