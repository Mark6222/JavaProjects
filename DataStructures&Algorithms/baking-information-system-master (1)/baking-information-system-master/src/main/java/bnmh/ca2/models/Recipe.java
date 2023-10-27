package bnmh.ca2.models;

import java.io.Serializable;

public class Recipe implements Serializable {



    //fields
    private BakedGood parent;
    private Recipe nextRecipe;
    private RecipeItem head;
    private String name;
    private String desc;



    //constructor
    public Recipe(BakedGood parent, Recipe nextRecipe, RecipeItem head, String name, String desc) {
        setParent(parent);
        setNextRecipe(nextRecipe);
        setHead(head);
        setName(name);
        setDesc(desc);
    }



    //getters
    public BakedGood getParent() {
        return parent;
    }

    public Recipe getNextRecipe() {
        return nextRecipe;
    }

    public RecipeItem getHead() {
        return head;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }



    //setters
    public void setParent(BakedGood parent) {
        this.parent = parent;
    }

    public void setNextRecipe(Recipe nextRecipe) {
        this.nextRecipe = nextRecipe;
    }

    public void setHead(RecipeItem head) {
        this.head = head;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



}
