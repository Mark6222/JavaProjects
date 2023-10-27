package bnmh.ca2.models;

import java.io.Serializable;

public class RecipeItem implements Serializable {



    //fields
    private Recipe parent;
    private RecipeItem nextRecipeItem;
    private String name;
    private int amount;
    private String type;



    //constructor
    public RecipeItem(Recipe parent, RecipeItem nextRecipeItem, String name, int amount, String type) {
        this.parent = parent;
        this.nextRecipeItem = nextRecipeItem;
        this.name = name;
        this.amount = amount;
        this.type = type;
    }



    //getters
    public Recipe getParent() {
        return parent;
    }

    public RecipeItem getNextRecipeItem() {
        return nextRecipeItem;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }



    //setters
    public void setParent(Recipe parent) {
        this.parent = parent;
    }

    public void setNextRecipeItem(RecipeItem nextRecipeItem) {
        this.nextRecipeItem = nextRecipeItem;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }



}
