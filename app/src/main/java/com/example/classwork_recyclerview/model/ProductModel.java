package com.example.classwork_recyclerview.model;

public class ProductModel {
    private int id;
    private String title;
    private String imagePath;
    private double price;

    public ProductModel(int id, String title, String imagePath, double price) {
        this.id = id;
        this.title = title;
        this.imagePath = imagePath;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public double getPrice() {
        return price;
    }
}
