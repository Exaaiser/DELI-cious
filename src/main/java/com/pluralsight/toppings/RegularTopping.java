package com.pluralsight.toppings;

public class RegularTopping extends Topping {
    public RegularTopping(String name) {
        super(name, 0.0); // Normal toppingler ücretsiz olduğu için fiyat 0
    }

    @Override
    public double getPrice() {
        return 0.0; // Fiyat her zaman 0
    }
}