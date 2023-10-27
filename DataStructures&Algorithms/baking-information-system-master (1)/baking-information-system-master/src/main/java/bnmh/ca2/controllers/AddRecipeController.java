package bnmh.ca2.controllers;

import bnmh.ca2.models.Recipe;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddRecipeController {

    @FXML
    private TextField nameText;
    @FXML
    private TextField descText;
    @FXML
    private Button addRecipeButton;
    @FXML
    private Button backButton;

    public void initialize() {

    }

    public void OnAddRecipeButton() throws IOException {

        if(nameText.getText() != null && descText.getText() != null) {

            Recipe rec = new Recipe(MenuController.bg, null, null, nameText.getText(), descText.getText());
            rec.setNextRecipe(MenuController.bg.getHead());
            MenuController.bg.setHead(rec);

            OnBackButton();

        }

    }

    public void OnBackButton() throws IOException {
        FXMLLoader detailsView = new FXMLLoader(AddGoodsController.class.getResource("baked-good-details.fxml"));
        backButton.getScene().setRoot(detailsView.load());
    }

}
