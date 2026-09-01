package com.example.classwork_recyclerview.model;

import java.util.List;

public class Product {
    private int id;
    private String title;
    private String description;
    private double price;
    private double discountPercentage;
    private String thumbnail;
    private List<String> images;

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public double getDiscountPercentage() { return discountPercentage; }
    public String getThumbnail() { return thumbnail; }
    public List<String> getImages() { return images; }
}
