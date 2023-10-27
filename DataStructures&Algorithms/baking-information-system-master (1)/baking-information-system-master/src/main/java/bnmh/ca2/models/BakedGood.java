package bnmh.ca2.models;

import bnmh.ca2.utils.LinkedNode;

import java.io.Serializable;

public class BakedGood implements Serializable {



    //fields
    private BakedGood nextGood;
    private Recipe head;
    private String name;
    private String origin;
    private String desc;
    private String filepath;



    //constructor
    public BakedGood(BakedGood nextGood, Recipe head, String name, String origin, String desc, String filepath) {
        setNextGood(nextGood);
        setHead(head);
        setName(name);
        setOrigin(origin);
        setDesc(desc);
        setFilepath(filepath);
    }



    //getters
    public BakedGood getNextGood() {
        return nextGood;
    }

    public Recipe getHead() {
        return head;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDesc() {
        return desc;
    }

    public String getFilepath() {
        return filepath;
    }



    //setters
    public void setNextGood(BakedGood nextGood) {
        this.nextGood = nextGood;
    }

    public void setHead(Recipe head) {
        this.head = head;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }



}
