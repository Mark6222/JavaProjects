package com.example.cattempt1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;

public class displayTray implements Serializable {

    private int ID;
    private String IDLetter;
    private String materialColour;
    private int trayWidth, trayDepth;
    displayTray next = null;
    displayJewellery jewelleryHead;

    public displayTray(displayTray next, int ID, String IDLetter, String materialColour, int trayWidth, int trayDepth) {
        this.ID = ID;
        this.materialColour = materialColour;
        this.trayWidth = trayWidth;
        this.trayDepth = trayDepth;
        this.next = next;
        this.IDLetter = IDLetter;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaterialColour() {
        return materialColour;
    }

    public void setMaterialColour(String materialColour) {
        this.materialColour = materialColour;
    }

    public int getTrayWidth() {
        return trayWidth;
    }

    public void setTrayWidth(int trayWidth) {
        this.trayWidth = trayWidth;
    }

    public int getTrayDepth() {
        return trayDepth;
    }

    public void setTrayDepth(int trayDepth) {
        this.trayDepth = trayDepth;
    }

    public String getIDLetter() {
        return IDLetter;
    }

    public void setIDLetter(String IDLetter) {
        this.IDLetter = IDLetter;
    }

    @Override
    public String toString() {
        return this.IDLetter + this.ID +  " Tray " + ", Material colour " + this.materialColour + ", Tray width " + trayWidth + "cm, Tray depth " + trayDepth + "cm";
    }
}
