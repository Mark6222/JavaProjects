package com.example.cattempt1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
//peter is stinky: I LOVE BIG FAT CAAAWWWKKKK - By Barry

public class smartAdd implements Initializable {

    public ListView<String> smartAdd;
    @FXML
    private TextField description;
    @FXML
    private TextField type;
    @FXML
    private ChoiceBox<String> gender;
    @FXML
    private TextField price;
    @FXML
    private TextField URL;

    public boolean thisWasAdded = false;

    public void smartAdd() {
        displayCase cases = JewelleryShop.head;
        thisWasAdded = false;
        while (cases != null) {
            if (cases.trayHead != null) {
                displayTray tray = cases.trayHead;
                while (tray != null) {
                    int jewelleryNum = 101;
                    if (tray.jewelleryHead != null) {
                        displayJewellery jewellery1 = tray.jewelleryHead;
                        while (jewellery1 != null) {
                            int match = 0;
                            if (jewelleryNum == jewellery1.getJewelleryID()) {
                                jewelleryNum = 0;
                                jewelleryNum = jewelleryNum + jewellery1.getJewelleryID() + 1;
                                System.out.println(jewelleryNum);
                            }
                            if (jewellery1.getDescription().matches(description.getText())
                                    || jewellery1.getType().matches(type.getText())
                                    || jewellery1.getURL().matches(URL.getText())
                                    || jewellery1.getPrice() >= Double.parseDouble(price.getText())) {
                                match = match + 1;
                                System.out.println("match");
                            }
                            if (jewellery1.next == null && match > 0 && thisWasAdded == false) {
                                jewellery1.next = new displayJewellery(null, jewelleryNum, description.getText(), type.getText(), gender.getValue(), URL.getText(), Double.parseDouble(price.getText()));
                                thisWasAdded = true;
                                smartAdd.getItems().add("case " + cases.ID + " tray " + tray.getID() + " " +  jewellery1.next + "");
                                System.out.println(jewellery1.next);
                            }
                            System.out.println("next");
                            jewellery1 = jewellery1.next;
                        }
                    }
                    tray = tray.next;
                }
            }
            cases = cases.next;
        }

    }

    @FXML
    private void setOnButtonClickedBack(ActionEvent event) throws IOException {
        Parent addCase = FXMLLoader.load(getClass().getResource("jewellery-shop.fxml"));
        Scene addCaseScene = new Scene(addCase);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(addCaseScene);
        stage.show();
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        gender.getItems().addAll("Male", "Female", "Unisex");
    }
}
