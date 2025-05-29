package com.pluralsight.builder;



import com.pluralsight.Chip;

import java.util.Scanner;

public class ChipBuilder {
    private static final Scanner scanner = new Scanner(System.in);

    public static com.pluralsight.Chip build(Scanner scanner) {
        String chipName;
        while (true) {
            System.out.println("\n--- Choose Chips ---");
            System.out.println("1) Lays");
            System.out.println("2) Doritos");
            System.out.println("3) Cheetos");
            System.out.println("4) Ruffles");
            System.out.println("0) Back");
            System.out.print("Your choose: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": chipName = "Lays"; break;
                case "2": chipName = "Doritos"; break;
                case "3": chipName = "Cheetos"; break;
                case "4": chipName = "Ruffles"; break;
                case "0": return null; // Geri dönmek için null döndür
                default:
                    System.out.println("Invalid Option. Please Try Again");
                    continue;
            }
            break; // Geçerli seçim yapıldı, döngüden çık
        }
        System.out.println(chipName + " cips eklendi.");
        return new Chip(chipName);
    }
}