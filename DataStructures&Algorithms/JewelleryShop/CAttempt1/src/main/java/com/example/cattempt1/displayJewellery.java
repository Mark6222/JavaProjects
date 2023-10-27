package com.example.cattempt1;

import java.io.Serializable;

public class displayJewellery implements Serializable {
    private int jewelleryID;
    private String description;
    private String type;
    private String gender;
    private String URL;
    private double price;
    displayJewellery next;
    displayMaterial material;

    public displayJewellery(displayJewellery next,int jewelleryID, String description, String type, String gender, String URL, double price) {
        this.description = description;
        this.type = type;
        this.gender = gender;
        this.URL = URL;
        this.next = next;
        this.jewelleryID = jewelleryID;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getJewelleryID() {
        return jewelleryID;
    }

    public void setJewelleryID(int jewelleryID) {
        this.jewelleryID = jewelleryID;
    }

    public displayJewellery getNext() {
        return next;
    }

    public void setNext(displayJewellery next) {
        this.next = next;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public displayMaterial getMaterial() {
        return material;
    }

    public void setMaterial(displayMaterial material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return jewelleryID + " Jewellery" + ", description " + description + ", type " + type + ", gender " + gender + ", URL " + URL + ", price " + price;
    }
}