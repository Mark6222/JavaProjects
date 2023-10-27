package com.example.cattempt1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewAllStock implements Initializable {

    public ListView<String> viewAllStock;

    public void addAllStock(){
        displayCase cases = JewelleryShop.head;
        while(cases != null){
            if(cases.trayHead != null){
                displayTray tray = cases.trayHead;
                while(tray != null){
                    if(tray.jewelleryHead != null){
                        displayJewellery jewellery = tray.jewelleryHead;
                        while(jewellery != null){
                            viewAllStock.getItems().add("case "+ cases.ID + "tray " + tray.getID() + " " + jewellery + "");
                            jewellery = jewellery.next;
                        }
                    }
                    tray = tray.next;
                }
            }
            cases = cases.next;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    addAllStock();
    }

    @FXML
    private void setOnButtonClickedHome(ActionEvent event) throws IOException {
        Parent addCase = FXMLLoader.load(getClass().getResource("Jewellery-shop.fxml"));
        Scene addCaseScene = new Scene(addCase);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(addCaseScene);
        stage.show();
    }
}
