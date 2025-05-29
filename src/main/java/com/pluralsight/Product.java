package com.pluralsight;

public abstract class Product implements Item { // Item arayüzünü uyguluyor
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override // Item arayüzünden geliyor
    public String getName() {
        return name;
    }

    @Override // Item arayüzünden geliyor
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - $" + String.format("%.2f", price);
    }
}