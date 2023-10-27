package com.example.cattempt1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class searchItems {

    public ListView<String> search;
    public TextField searched;

    public void searchList() {
        String item = searched.getText();
        displayCase cases = JewelleryShop.head;
        while (cases != null) {
            if (cases.trayHead != null) {
                displayTray tray = cases.trayHead;
                while (tray != null) {
                    if (tray.jewelleryHead != null) {
                        displayJewellery jewellery = tray.jewelleryHead;
                        while (jewellery != null) {
                            if (jewellery.material != null) {
                                displayMaterial material = jewellery.material;
                                while (material != null) {
                                    if (material.getDescription().matches(item)
                                            || material.getName().matches(item)
                                            || material.getQuality().matches(item)
                                            || material.getQuantity().matches(item)
                                    ) {
                                        search.getItems().add("case " + cases.ID + "tray " + tray.getID() + " jewellery " + jewellery.getJewelleryID() + material + "");
                                    }
                                    material = material.next;
                                }
                            }
                            if (jewellery.getDescription().matches(item)
                                    || jewellery.getGender().matches(item)
                                    || jewellery.getType().matches(item)
                            ) {
                                search.getItems().add("case " + cases.ID + "tray " + tray.getID() + jewellery + "");
                            }
                            jewellery = jewellery.next;
                        }
                    }
                    tray = tray.next;
                }
            }
            cases = cases.next;
        }
    }

    @FXML
    private void setOnButtonClickedHome(ActionEvent event) throws IOException {
        Parent addCase = FXMLLoader.load(getClass().getResource("jewellery-shop.fxml"));
        Scene addCaseScene = new Scene(addCase);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(addCaseScene);
        stage.show();
    }
}
