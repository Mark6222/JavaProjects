package com.example.cattempt1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class displayCaseController {

    public ChoiceBox<String> mount;
    public TextField textID;
    boolean lighting = false;
    int caseID = 100;

    public void lit() {
        lighting = true;
    }

    public void unlit() {
        lighting = false;
    }

    public void setOnButtonClickedHome(ActionEvent event) throws IOException {
        addLinkedList();
        Parent jewelleryStore = FXMLLoader.load(getClass().getResource("Jewellery-shop.fxml"));
        Scene jewelleryStoreScene = new Scene(jewelleryStore);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(jewelleryStoreScene);
        stage.show();

    }

    @FXML
    private void setOnButtonClickedBack(ActionEvent event) throws IOException {
        Parent addCase = FXMLLoader.load(getClass().getResource("jewellery-shop.fxml"));
        Scene addCaseScene = new Scene(addCase);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(addCaseScene);
        stage.show();
    }


    public void addLinkedList() {
        caseID = caseID + Integer.parseInt(textID.getText());
        if (isEmpty()) {
            JewelleryShop.head = new displayCase(null, caseID, mount.getValue(), lighting);
            System.out.println(JewelleryShop.head);
        } else {
            displayCase temp = JewelleryShop.head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new displayCase(null, caseID, mount.getValue(), lighting);
            System.out.println(temp.next);
        }
    }

    public void initialize() {
        mount.getItems().addAll("Mounted", "Freestanding");
    }

    boolean isEmpty() {
        return JewelleryShop.head == null;
    }
}
