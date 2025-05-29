package com.pluralsight.toppings;

public class CheeseTopping extends Topping {
    private boolean isExtra;

    public CheeseTopping(String name, double basePrice, boolean isExtra) {
        super(name, basePrice); // Temel fiyatı Product'a gönder
        this.isExtra = isExtra;
    }

    // Ekstra peynirin fiyatını hesaplamak için getPrice metodunu override ediyoruz
    @Override
    public double getPrice() {
        return super.getPrice() + (isExtra ? 0.30 : 0); //  Basic Extra
    }

    public boolean isExtra() {
        return isExtra;
    }

    public void setExtra(boolean extra) {
        isExtra = extra;
    }

    @Override
    public String toString() {
        return (isExtra ? "Ekstra " : "") + getName();
    }
}
