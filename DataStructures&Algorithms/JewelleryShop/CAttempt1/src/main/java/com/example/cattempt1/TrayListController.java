package com.example.cattempt1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TrayListController implements Initializable {

    public ListView<String> displayTrayList;

    public void addTray(int caseID) {
        displayCase temp = JewelleryShop.head;
        while (temp.ID != caseID) {
            temp = temp.next;
        }
        if (temp.ID == caseID) {
            if (temp.trayHead != null) {
                double price = 0;
                double price3 = 0;
                if (temp.trayHead.jewelleryHead != null) {
                    displayJewellery jewellery = temp.trayHead.jewelleryHead;
                    while (jewellery != null) {
                        price = price + jewellery.getPrice();
                        jewellery = jewellery.next;
                    }
                }
                displayTrayList.getItems().add(temp.trayHead + ", total price " + price);
                displayTray tray = temp.trayHead;
                if (tray != null) {
                    while (tray != null) {
                        price3 = 0;
                        if(tray.next != null) {
                            if (tray.next.jewelleryHead != null) {
                                displayJewellery jewellery2 = tray.next.jewelleryHead;
                                while (jewellery2 != null) {
                                    System.out.println(jewellery2.getPrice());
                                    price3 = price3 + jewellery2.getPrice();
                                    jewellery2 = jewellery2.next;
                                }
                            }
                        }
                        tray = tray.next;
                        if(tray != null) {
                            displayTrayList.getItems().add(tray + ", total price " + price3);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addTray(JewelleryShop.caseID);
        displayTrayList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> JewelleryShop.trayID = Integer.parseInt(displayTrayList.getSelectionModel().getSelectedItem().substring(1, 4)));

    }

    @FXML
    private void setOnButtonClickedHome(ActionEvent event) throws IOException {
        Parent addTray = FXMLLoader.load(getClass().getResource("Jewellery-shop.fxml"));
        Scene addTrayScene = new Scene(addTray);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(addTrayScene);
        stage.show();
    }

    @FXML
    private void setOnButtonClickedTray(ActionEvent event) throws IOException {
        Parent addTray = FXMLLoader.load(getClass().getResource("AddTray.fxml"));
        Scene addTrayScene = new Scene(addTray);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(addTrayScene);
        stage.show();
    }

    @FXML
    private void setOnButtonClickedJewelleryList(MouseEvent event) throws IOException {
        Parent TrayList = FXMLLoader.load(getClass().getResource("JewelleryList.fxml"));
        Scene TrayListScene = new Scene(TrayList);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(TrayListScene);
        stage.show();
    }


}
