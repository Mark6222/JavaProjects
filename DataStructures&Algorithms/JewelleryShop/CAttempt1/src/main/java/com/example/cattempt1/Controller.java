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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public ListView<String> displayCaseList;
    public TextField save;
    public TextField load;
    boolean delete = false;
    public int totalPrice;
    String p = "fuck you";

    public void delete() {
        delete = true;
    }

    public void selectCase() {
        delete = false;
    }

    public void setHead(displayCase head){
        JewelleryShop.head = head;
    }

    public void addCase() {
        System.out.println("hi");
        if (JewelleryShop.head != null) {
            double price = 0;
            double price3 = 0;
            displayCase temp = JewelleryShop.head;
            if (temp != null) {
                while (temp != null) {
                    price3 = 0;
                    if (temp.trayHead != null) {
                        displayTray tray2 = temp.trayHead;
                        while (tray2 != null) {
                            if (tray2.jewelleryHead != null) {
                                displayJewellery jewellery2 = tray2.jewelleryHead;
                                while (jewellery2 != null) {
                                    price3 = price3 + jewellery2.getPrice();
                                    totalPrice = (int) (totalPrice + jewellery2.getPrice());
                                    jewellery2 = jewellery2.next;
                                    System.out.println(price3);
                                }
                            }
                            tray2 = tray2.next;
                        }
                    }
                    if (temp != null) {
                        displayCaseList.getItems().add(temp + ", total price " + price3);
                    }
                    temp = temp.next;
                }
            }
        }
    }

    public void deleteCase() {
        if (delete) {
            int caseNum = 1;
            displayCase caseDelete = JewelleryShop.head;
            displayCase delete = null;
            if (caseDelete.ID == JewelleryShop.caseID) {
                JewelleryShop.head = caseDelete.next;
                displayCaseList.getItems().remove(0);
            } else {
                while (caseDelete != null) {
                    if (caseDelete.next.ID == JewelleryShop.caseID) {
                        delete = caseDelete.next;
                        caseDelete.next = delete.next;
                        displayCaseList.getItems().remove(caseNum);
                    }
                    caseDelete = caseDelete.next;
                    caseNum = caseNum + 1;
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {;
        addCase();
        displayCaseList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> JewelleryShop.caseID = Integer.parseInt(displayCaseList.getSelectionModel().getSelectedItem().substring(0, 3)));
    }

    @FXML
    private void setOnButtonClickedTrayList(MouseEvent event) throws IOException {
        if (delete == false) {
            Parent TrayList = FXMLLoader.load(getClass().getResource("TrayList.fxml"));
            Scene TrayListScene = new Scene(TrayList);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(TrayListScene);
            stage.show();
        }
    }

    @FXML
    private void setOnButtonClickedCase(ActionEvent event) throws IOException {
        Parent addCase = FXMLLoader.load(getClass().getResource("AddCase.fxml"));
        Scene addCaseScene = new Scene(addCase);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(addCaseScene);
        stage.show();
    }

    @FXML
    private void setOnButtonClickedResetAll(ActionEvent event) throws IOException {
        JewelleryShop.head = null;
        Parent addCase = FXMLLoader.load(getClass().getResource("Jewellery-shop.fxml"));
        Scene addCaseScene = new Scene(addCase);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(addCaseScene);
        stage.show();
    }

    @FXML
    private void setOnButtonClickedReload(ActionEvent event) throws IOException {
        Parent addCase = FXMLLoader.load(getClass().getResource("Jewellery-shop.fxml"));
        Scene addCaseScene = new Scene(addCase);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(addCaseScene);
        stage.show();
    }

    @FXML
    private void setOnButtonClickedAllStock(ActionEvent event) throws IOException {
        Parent addCase = FXMLLoader.load(getClass().getResource("veiwAllStock.fxml"));
        Scene addCaseScene = new Scene(addCase);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(addCaseScene);
        stage.show();
    }

    @FXML
    private void setOnButtonClickedSearch(ActionEvent event) throws IOException {
        Parent addCase = FXMLLoader.load(getClass().getResource("Search.fxml"));
        Scene addCaseScene = new Scene(addCase);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(addCaseScene);
        stage.show();
    }

    @FXML
    private void setOnButtonClickedSmartAdd(ActionEvent event) throws IOException {
        Parent addCase = FXMLLoader.load(getClass().getResource("smartAdd.fxml"));
        Scene addCaseScene = new Scene(addCase);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(addCaseScene);
        stage.show();
    }

    public void save() throws Exception {
        ObjectOutputStream outputStream;
        outputStream = new ObjectOutputStream(new FileOutputStream(save.getText() + ".xml"));
        outputStream.writeObject(JewelleryShop.head);
        outputStream.close();
    }

    public void load() throws Exception {
        ObjectInputStream inputStream;
        inputStream = new ObjectInputStream(new FileInputStream(load.getText() + ".xml"));
        JewelleryShop.head = (displayCase) inputStream.readObject();
        inputStream.close();
        addCase();
    }
}