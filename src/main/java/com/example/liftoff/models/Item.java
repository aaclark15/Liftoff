package com.example.liftoff.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, message = "Name cannot be empty")
    private String name;

    @NotNull
    @Size(min=1, message = "Store cannot be empty")
    private String store;

    private double price;
    private String color;
    private String size;
    private String details;
    private boolean purchased;
    private double purch_price;

    @OneToMany
    @JoinColumn(name = "item_id")
    private List<Pictures> pictures = new ArrayList<>();

    @ManyToOne
    private Category category;

    //contructors
    public Item(String name, String store, double price, String color, String size,
                String details, boolean purchased, double purch_price) {
        this.name = name;
        this.store = store;
        this.price = price;
        this.color = color;
        this.size = size;
        this.details = details;
        this.purchased = purchased;
        this.purch_price = purch_price;
    }
    public Item(String name, String store) {
        this.name = name;
        this.store = store;
    }
    public Item() { }


    //getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public double getPurch_price() {
        return purch_price;
    }

    public void setPurch_price(double purch_price) {
        this.purch_price = purch_price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Pictures> getPictures() {
        return pictures;
    }

    public void setPictures(List<Pictures> pictures) {
        this.pictures = pictures;
    }
}
