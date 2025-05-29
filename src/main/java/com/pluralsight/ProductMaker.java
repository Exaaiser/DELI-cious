package com.pluralsight;

import java.io.*;
import java.util.*;

public class ProductMaker {

    public static List<Product> loadProducts(String filename) {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 3) continue;

                String type = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);


                switch (type.toLowerCase()) {
                    case "sandwich":
                        products.add(new Product(name, price) {
                            @Override
                            public String getName() {

                                return super.getName(); }
                            @Override
                            public double getPrice() {

                                return super.getPrice(); }
                        });
                        break;
                    case "drink":
                        products.add(new Product(name, price) { // Anonim iç sınıf Product'tan türetildi
                            @Override
                            public String getName() {

                                return super.getName(); }
                            @Override
                            public double getPrice() {

                                return super.getPrice(); }
                        });
                        break;
                    case "chips":
                        products.add(new Product(name, price) {
                            @Override
                            public String getName() {

                                return super.getName(); }
                            @Override
                            public double getPrice() {

                                return super.getPrice(); }
                        });
                        break;
                    default:
                        System.out.println("Unknown product type: " + type);
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading products: " + e.getMessage());
            // I was got error this part I went to AI for helping and I add NumberFormatException
        }

        return products;
    }
}