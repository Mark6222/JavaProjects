package bnmh.ca2.models;

import java.io.Serializable;

public class Ingredient implements Serializable {



    //fields
    private Ingredient nextIngredient;
    private String name;
    private String desc;
    private int calories;



    //constructor
    public Ingredient(Ingredient nextIngredient, String name, String desc, int calories) {
        setNextIngredient(nextIngredient);
        setName(name);
        setDesc(desc);
        setCalories(calories);
    }



    //getters
    public Ingredient getNextIngredient() {
        return nextIngredient;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getCalories() {
        return calories;
    }



    //setters
    public void setNextIngredient(Ingredient nextIngredient) {
        this.nextIngredient = nextIngredient;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }



}
