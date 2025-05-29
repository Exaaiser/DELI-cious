package com.pluralsight;

import com.pluralsight.Product;

public class Chip extends Product {
    public Chip(String name) {
        // Cipslerin tek bir fiyatı var: 1.50
        super(name, 1.50);
    }

    @Override
    public double getPrice() {
        return 1.50; // Cips fiyatı her zaman 1.50
    }
}