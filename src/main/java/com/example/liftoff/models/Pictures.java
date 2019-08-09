package com.example.liftoff.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.sql.Blob;

@Entity
public class Pictures {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String picType;

    @NotNull
    private Blob picData;

    @ManyToOne
    private Item item;

    //constructors
    public Pictures(String picType, Blob picData) {
        this.picType = picType;
        this.picData = picData;
    }
    public Pictures() { }

    //getters and setters
    public int getId() {
        return id;
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType;
    }

    public Blob getPicData() {
        return picData;
    }

    public void setPicData(Blob picData) {
        this.picData = picData;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
