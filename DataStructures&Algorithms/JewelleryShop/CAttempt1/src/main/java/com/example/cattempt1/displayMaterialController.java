package com.example.cattempt1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class displayMaterialController {

    public TextField name;
    public TextField description;
    public TextField Quality;
    public TextField Quantity;

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
        while (temp3.getJewelleryID() != JewelleryShop.jewelleryID) {
            temp3 = temp3.next;
        }
        if (temp3.getJewelleryID() == JewelleryShop.jewelleryID) {
            if (temp3.material == null) {
                temp3.material = new displayMaterial(null, name.getText(), description.getText(), Quality.getText(), Quantity.getText());
                System.out.println(temp3.material);
            } else {
                displayMaterial material = temp3.material;
                while (material.next != null) {
                    material = material.next;
                }
                material.next = new displayMaterial(null, name.getText(), description.getText(), Quality.getText(), Quantity.getText());
                System.out.println(material.next);
            }
        }
    }

    public void setOnButtonClickedAddMaterial(ActionEvent event) throws IOException {
        addMaterial();
        Parent jewelleryStore = FXMLLoader.load(getClass().getResource("MaterialList.fxml"));
        Scene jewelleryStoreScene = new Scene(jewelleryStore);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(jewelleryStoreScene);
        stage.show();
    }

    @FXML
    private void setOnButtonClickedBack(ActionEvent event) throws IOException {
        Parent addCase = FXMLLoader.load(getClass().getResource("MaterialList.fxml"));
        Scene addCaseScene = new Scene(addCase);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(addCaseScene);
        stage.show();
    }

}
