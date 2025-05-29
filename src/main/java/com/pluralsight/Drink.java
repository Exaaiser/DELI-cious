package com.pluralsight;

public class Drink extends Product {
    private String size; // Small, Medium, Large

    public Drink(String name, String size) {
        super(name, 0.0); // Fiyatı boyuta göre hesaplanacak
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    @Override
    public double getPrice() {
        switch (size.toLowerCase()) {
            case "small":
                return 2.00;
            case "medium":
                return 2.50;
            case "large":
                return 3.00;
            default:
                return 0.0; // Geçersiz boyut
        }
    }

    @Override
    public String toString() {
        return getName() + " (" + size + ") - $" + String.format("%.2f", getPrice());
    }
}