package com.example.cattempt1;

import java.io.Serializable;

public class displayMaterial implements Serializable {
    String name;
    String description;
    String quality;
    String quantity;
    displayMaterial next;

    public displayMaterial(displayMaterial next, String name, String description, String quality, String quantity) {
        this.name = name;
        this.description = description;
        this.next = next;
        this.quantity = quantity;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Material name " + name + ", description " + description + ", quality " + quality + ", quantity " + quantity;
    }
}
