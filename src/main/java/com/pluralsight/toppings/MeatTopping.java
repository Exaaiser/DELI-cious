package com.pluralsight.toppings;

public class MeatTopping extends Topping {
    private boolean isExtra;

    public MeatTopping(String name, double basePrice, boolean isExtra) {
        super(name, basePrice); // Send basicPrice to product
        this.isExtra = isExtra;
    }

    // For calculating extra price of meet we add ovveride
    @Override
    public double getPrice() {
        return super.getPrice() + (isExtra ? 0.50 : 0); // Default extra price
    }

    public boolean isExtra() {
        return isExtra;
    }

    public void setExtra(boolean extra) {
        isExtra = extra;
    }

    @Override
    public String toString() {
        return (isExtra ? "Extra " : "") + getName();
    }
}