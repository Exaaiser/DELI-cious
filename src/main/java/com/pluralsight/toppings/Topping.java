package com.pluralsight.toppings;

import com.pluralsight.Product;

// Soyut sınıf
public abstract class Topping extends Product {
    public Topping(String name, double price) {
        super(name, price);
    }
}