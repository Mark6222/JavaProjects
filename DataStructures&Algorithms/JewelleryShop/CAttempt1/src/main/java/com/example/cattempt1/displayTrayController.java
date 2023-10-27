package com.example.cattempt1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class displayTrayController {
    public TextField trayID;
    public ChoiceBox<String> materialColour;
    public TextField trayWidth;
    public TextField trayDepth;
    public TextField idLetter;
    int trayNum = 100;


    public void setOnButtonClickedBack(ActionEvent event) throws IOException {
        Parent jewelleryStore = FXMLLoader.load(getClass().getResource("TrayList.fxml"));
        Scene jewelleryStoreScene = new Scene(jewelleryStore);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(jewelleryStoreScene);
        stage.show();
    }

    public void setOnButtonClickedAddTray(ActionEvent event) throws IOException {
        addTray();
        Parent jewelleryStore = FXMLLoader.load(getClass().getResource("TrayList.fxml"));
        Scene jewelleryStoreScene = new Scene(jewelleryStore);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(jewelleryStoreScene);
        stage.show();
    }

    public void addTray() {
        trayNum = trayNum + Integer.parseInt(trayID.getText());
        displayCase temp = JewelleryShop.head;
        while (temp.ID != JewelleryShop.caseID) {
            temp = temp.next;
        }
        if (temp.ID == JewelleryShop.caseID) {
            if (temp.trayHead == null) {
                temp.trayHead = new displayTray(null, trayNum, idLetter.getText(), materialColour.getValue(), Integer.parseInt(trayWidth.getText()), Integer.parseInt(trayDepth.getText()));
                System.out.println(temp.trayHead);
            } else {
                displayTray tray = temp.trayHead;
                while (tray.next != null) {
                    tray = tray.next;
                }
                tray.next = new displayTray(null, trayNum, idLetter.getText(), materialColour.getValue(), Integer.parseInt(trayWidth.getText()), Integer.parseInt(trayDepth.getText()));
                System.out.println(tray.next);
            }
        }
    }

    public void initialize() {
        materialColour.getItems().addAll("red", "yellow", "blue", "green", "purple", "brown", "gray", "black", "silver", "pink", "gold", "cyan");
    }
}
