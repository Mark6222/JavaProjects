package com.example.cattempt1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

public class displayCase implements Serializable {
    int ID;
    String freeWall;
    boolean lighting;
    displayCase next = null;
    displayTray trayHead = null;


    public displayCase(displayCase next, int ID, String freeWall, boolean lighting) {
        this.ID = ID;
        this.freeWall = freeWall;
        this.next = next;
        this.lighting = lighting;
    }

    public String getFreeWall() {
        return freeWall;
    }

    public displayCase getNext() {
        return next;
    }

    public void setNext(displayCase next) {
        this.next = next;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String isFreeWall() {
        return freeWall;
    }

    public void setFreeWall(String freeWall) {
        this.freeWall = freeWall;
    }

    public boolean isLighting() {
        return lighting;
    }

    public void setLighting(boolean lighting) {
        this.lighting = lighting;
    }

    public String light() {
        String lit = "";
        if (lighting) {
            lit = "lit";
        } else {
            lit = "unlit";
        }
        return lit;
    }

    @Override
    public String toString() {
        return this.ID + " " + this.freeWall + " and " + light();
    }
}
