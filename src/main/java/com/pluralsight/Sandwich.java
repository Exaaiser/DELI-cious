package com.pluralsight;

import com.pluralsight.toppings.CheeseTopping;
import com.pluralsight.toppings.MeatTopping;
import com.pluralsight.toppings.SauceTopping;
import com.pluralsight.toppings.Topping;

import java.util.ArrayList;
import java.util.List;

public class Sandwich extends Product {
    private int size; // 4, 8, 12 inç
    private String breadType; // white, wheat, rye, wrap
    private boolean isToasted;
    private List<Topping> toppings;
    private List<SauceTopping> sauces;
    private List<SideItem> sides; // Au jus, sauce gibi yan ürünler

    public Sandwich(int size, String breadType, boolean isToasted) {
        // Sandwich'in adı boyut ve ekmek tipine göre belirlenebilir
        super(size + "\" " + breadType + " Sandviç", 0.0); // Başlangıç fiyatı 0, calculatePrice ile güncellenecek
        this.size = size;
        this.breadType = breadType;
        this.isToasted = isToasted;
        this.toppings = new ArrayList<>();
        this.sauces = new ArrayList<>();
        this.sides = new ArrayList<>();
    }

    // Getters for properties
    public int getSize() {
        return size;
    }

    public String getBreadType() {
        return breadType;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public List<SauceTopping> getSauces() {
        return sauces;
    }

    public List<SideItem> getSides() {
        return sides;
    }

    // Add methods for various components
    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void addSauce(SauceTopping sauce) {
        this.sauces.add(sauce);
    }

    public void addSide(SideItem side) {
        this.sides.add(side);
    }


    @Override
    public double getPrice() {
        double totalPrice = 0.0;

        // Ekmek ve boyuta göre temel fiyat
        switch (size) {
            case 4:
                totalPrice += 5.50; // White, Wheat, Rye, Wrap 4" için 5.50
                break;
            case 8:
                totalPrice += 7.00; // White, Wheat, Rye, Wrap 8" için 7.00
                break;
            case 12:
                totalPrice += 8.50; // White, Wheat, Rye, Wrap 12" için 8.50
                break;
        }

        // Topping fiyatları
        for (Topping topping : toppings) {
            if (topping instanceof MeatTopping) {
                MeatTopping meat = (MeatTopping) topping;
                switch (size) {
                    case 4:
                        totalPrice += meat.isExtra() ? 0.50 : 1.00; // Ekstra et 0.50, normal et 1.00
                        break;
                    case 8:
                        totalPrice += meat.isExtra() ? 1.00 : 2.00; // Ekstra et 1.00, normal et 2.00
                        break;
                    case 12:
                        totalPrice += meat.isExtra() ? 1.50 : 3.00; // Ekstra et 1.50, normal et 3.00
                        break;
                }
            } else if (topping instanceof CheeseTopping) {
                CheeseTopping cheese = (CheeseTopping) topping;
                switch (size) {
                    case 4:
                        totalPrice += cheese.isExtra() ? 0.30 : 0.75; // Ekstra peynir 0.30, normal peynir 0.75
                        break;
                    case 8:
                        totalPrice += cheese.isExtra() ? 0.60 : 1.50; // Ekstra peynir 0.60, normal peynir 1.50
                        break;
                    case 12:
                        totalPrice += cheese.isExtra() ? 0.90 : 2.25; // Ekstra peynir 0.90, normal peynir 2.25
                        break;
                }
            }
        }

        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(" (")
                .append(size).append("\", ").append(breadType).append(")");
        if (isToasted) {
            sb.append(" - Toasted");
        }
        sb.append("\n  Toppings: ");
        if (toppings.isEmpty() && sauces.isEmpty() && sides.isEmpty()) {
            sb.append("No");
        } else {
            for (Topping t : toppings) {
                sb.append(t.toString()).append(", ");
            }
            for (SauceTopping s : sauces) {
                sb.append(s.toString()).append(" (Sauce), ");
            }
            for (SideItem s : sides) {
                sb.append(s.toString()).append(" (Side Item), ");
            }
        }
        sb.append("\n Price: $").append(String.format("%.2f", getPrice()));
        return sb.toString();

        // I got this code block from  reddit and convert the "" parts to better usable format for my project
    }
}