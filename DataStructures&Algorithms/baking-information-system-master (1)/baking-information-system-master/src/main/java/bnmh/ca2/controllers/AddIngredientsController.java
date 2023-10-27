package bnmh.ca2.controllers;

import bnmh.ca2.models.BakedGood;
import bnmh.ca2.models.Ingredient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Locale;

public class AddIngredientsController {

    @FXML
    private Button backButton;
    @FXML
    private TextField nameText;
    @FXML
    private TextArea descText;
    @FXML
    private TextField calorieText;
    @FXML
    private Button addIngredientButton;

    public void initialize() {

        calorieText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*")) {
                calorieText.setText(newValue.replaceAll("[^[0-9]]", ""));
            }
        });

    }

    private int hash(Ingredient ing) {
        char[] temp = ing.getName().toLowerCase(Locale.ROOT).toCharArray();
        int key = 0;
        for(char c : temp) {
            key += c;
        }
        return key%MainApplication.ingHash.length;
    }

    public void OnAddIngredientButton() throws IOException {

        if(nameText.getText() != null && descText.getText() != null && calorieText.getText() != null) {

            Ingredient ing = new Ingredient(null, nameText.getText(), descText.getText(), Integer.parseInt(calorieText.getText()));
            MainApplication.ingList.add(ing);
            int i = hash(ing);
            System.out.println("\nAdding Ingredient to Hash Table ingHash:\nHash No: " + i);
            MainApplication.ingHash[i].add(ing);
            System.out.print("Ingredient ID: ");
            MainApplication.ingHash[i].list();
            System.out.println("\n");

            OnBackButton();

        }

    }

    public void OnBackButton() throws IOException {
        FXMLLoader menuView = new FXMLLoader(AddGoodsController.class.getResource("menu-view.fxml"));
        backButton.getScene().setRoot(menuView.load());
    }

}
