package com.example.classwork_recyclerview.model;

import java.util.Arrays;
import java.util.List;

public class ModelList {
    private int image;
    private String title;
    private String desc;
    private int price1;
    private int price2;

    public ModelList(int image, String title, String desc, int price1, int price2) {
        this.image = image;
        this.title = title;
        this.desc = desc;
        this.price1 = price1;
        this.price2 = price2;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getPrice1() {
        return price1;
    }

    public int getPrice2() {
        return price2;
    }



}
