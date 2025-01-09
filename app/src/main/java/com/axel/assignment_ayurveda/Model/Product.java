package com.axel.assignment_ayurveda.Model;

public class Product {
    private final int id;
    private final String name;
    private final int imageResId;
    private final double price;
    private final String description;
    private int quantity;


    public Product(int id, String name, int imageResId, double price, String description, int quantity) {
        this.id = id;
        this.name = name;
        this.imageResId = imageResId;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void incrementQuantity() {
        this.quantity++;
    }


    public void decrementQuantity() {
        if (this.quantity > 1) {
            this.quantity--;
        }
    }
}
