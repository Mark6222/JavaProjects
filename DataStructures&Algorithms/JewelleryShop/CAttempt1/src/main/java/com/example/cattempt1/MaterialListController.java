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

public class MaterialListController implements Initializable{

    public ListView<String> displayMaterialList;

    public void addMaterial() {
        displayCase temp = JewelleryShop.head;
        while (temp.ID != JewelleryShop.caseID) {
            temp = temp.next;
        }
        displayTray temp2 = temp.trayHead;
        while (temp2.getID() != JewelleryShop.trayID) {
            temp2 = temp2.next;
        }
        displayJewellery temp3 = temp2.jewelleryHead;
        while(temp3.getJewelleryID() != JewelleryShop.jewelleryID){
            temp3 = temp3.next;
        }
        if (temp3.getJewelleryID() == JewelleryShop.jewelleryID) {
            if (temp3.material != null) {
                displayMaterialList.getItems().add(temp3.material + "");
                if(temp3.material.next != null){
                    displayMaterialList.getItems().add(temp3.material.next + "");
                }
            } else {
                displayMaterial material = temp3.material;
                if (material != null) {
                    while (material.next != null) {
                        material = material.next;
                        displayMaterialList.getItems().add(material.next + "");
                    }
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addMaterial();
    }

    @FXML
    private void setOnButtonClickedAddMaterial(ActionEvent event) throws IOException {
        Parent addTray = FXMLLoader.load(getClass().getResource("addMaterial.fxml"));
        Scene addTrayScene = new Scene(addTray);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(addTrayScene);
        stage.show();
    }

    @FXML
    private void setOnButtonClickedBack(ActionEvent event) throws IOException {
        Parent addCase = FXMLLoader.load(getClass().getResource("JewelleryList.fxml"));
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

