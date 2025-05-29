package com.pluralsight.toppings;

public class SauceTopping extends Topping {
    public SauceTopping(String name) {
        super(name, 0.0); //  Sauces are free!!!!
    }

    @Override
    public double getPrice() {
        return 0.0; // Price every time is zero
    }
}