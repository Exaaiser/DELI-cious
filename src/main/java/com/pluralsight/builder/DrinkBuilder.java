package com.pluralsight.builder;

import com.pluralsight.Drink;

import java.util.Scanner;

public class DrinkBuilder {
    private static final Scanner scanner = new Scanner(System.in);

    public static Drink build(Scanner scanner) {
        String drinkName;
        String drinkSize;

        while (true) {
            System.out.println("\n--- Please SELECT Drink ---");
            System.out.println("1) Coke");
            System.out.println("2) Fanta");
            System.out.println("3) Sprite");
            System.out.println("4) Water");
            System.out.println("0) Back");
            System.out.print("Choose your drink: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": drinkName = "Coke"; break;
                case "2": drinkName = "Fanta"; break;
                case "3": drinkName = "Sprite"; break;
                case "4": drinkName = "Water"; break;
                case "0": return null; // Geri dönmek için null döndür
                default:
                    System.out.println("Invalid Option, please try again.");
                    continue;
            }
            break;
        }

        while (true) {
            System.out.println("\n--- Choose your Drink Size ---");
            System.out.println("1) Small ]");
            System.out.println("2) Mid ");
            System.out.println("3) Large ");
            System.out.print("Choose your size: ");
            String sizeChoice = scanner.nextLine();

            switch (sizeChoice) {
                case "1": drinkSize = "Small"; break;
                case "2": drinkSize = "Medium"; break;
                case "3": drinkSize = "Large"; break;
                default:
                    System.out.println("Invalid option. Please try again");
                    continue;
            }
            break;
        }
        System.out.println(drinkName + " (" + drinkSize + ") Drink added.");
        return new Drink(drinkName, drinkSize);
    }
}