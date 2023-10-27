package com.example.cattempt1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JewelleryListController implements Initializable {

    public ListView<String> displayJewelleryList;
    boolean delete = false;

    public void delete() {
        delete = true;
    }

    public void selectItem() {
        delete = false;
    }

    public void addJewellery() {
        displayCase temp = JewelleryShop.head;
        while (temp.ID != JewelleryShop.caseID) {
            temp = temp.next;
        }
        displayTray temp2 = temp.trayHead;
        if(null != temp2) {
            while (temp2.getID() != JewelleryShop.trayID) {
                temp2 = temp2.next;
            }
        }
        if (temp2.getID() == JewelleryShop.trayID) {
            if (temp2.jewelleryHead != null) {
                System.out.println(temp2.jewelleryHead);
                displayJewelleryList.getItems().add(temp2.jewelleryHead + "");
                if (temp2.jewelleryHead.next != null){
                    displayJewelleryList.getItems().add(temp2.jewelleryHead.next + "");
                }
            } else {
                displayJewellery jewellery = temp2.jewelleryHead;
                if (jewellery != null) {
                    while (jewellery.next != null) {
                        jewellery = jewellery.next;
                        displayJewelleryList.getItems().add(jewellery.next + "");
                    }
                }
            }
        }
    }

    public void deleteItem(){
        if (delete) {
            int itemNum = 1;
            displayJewellery delete = null;
            displayCase temp = JewelleryShop.head;
            while (temp.ID != JewelleryShop.caseID) {
                temp = temp.next;
            }
            displayTray temp2 = temp.trayHead;
            while (temp2.getID() != JewelleryShop.trayID) {
                temp2 = temp2.next;
            }
            displayJewellery itemDelete = temp2.jewelleryHead;
            if (itemDelete.getJewelleryID() == JewelleryShop.jewelleryID) {
                temp2.jewelleryHead = itemDelete.next;
                displayJewelleryList.getItems().remove(0);
            } else {
                while (itemDelete != null) {
                    if (itemDelete.next.getJewelleryID() == JewelleryShop.jewelleryID) {
                        delete = itemDelete.next;
                        itemDelete.next = delete.next;
                        displayJewelleryList.getItems().remove(itemNum);
                    }
                    itemDelete = itemDelete.next;
                    itemNum = itemNum + 1;
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addJewellery();
        displayJewelleryList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> JewelleryShop.jewelleryID = Integer.parseInt(displayJewelleryList.getSelectionModel().getSelectedItem().substring(0, 3)));
    }

    @FXML
    private void setOnButtonClickedMaterialList(MouseEvent event) throws IOException {
        if(delete == false) {
            Parent addTray = FXMLLoader.load(getClass().getResource("MaterialList.fxml"));
            Scene addTrayScene = new Scene(addTray);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(addTrayScene);
            stage.show();
        }
    }

    @FXML
    private void setOnButtonClickedJewellery(ActionEvent event) throws IOException {
        Parent addTray = FXMLLoader.load(getClass().getResource("AddJewellery.fxml"));
        Scene addTrayScene = new Scene(addTray);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(addTrayScene);
        stage.show();
    }

    @FXML
    private void setOnButtonClickedBack(ActionEvent event) throws IOException {
        Parent addCase = FXMLLoader.load(getClass().getResource("TrayList.fxml"));
        Scene addCaseScene = new Scene(addCase);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(addCaseScene);
        stage.show();
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
