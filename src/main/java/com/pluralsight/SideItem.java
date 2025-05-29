package com.pluralsight;

public class SideItem extends Product {
    public SideItem(String name) {
        super(name, 0.0); // Side Items are freee
    }

    @Override
    public double getPrice() {
        return 0.0; // Fiyat her zaman 0
    }
}