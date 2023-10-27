package bnmh.ca2.controllers;

import bnmh.ca2.models.BakedGood;
import bnmh.ca2.models.Ingredient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;

public class IngredientsDetailsController {

    public TextField Name;
    public TextField Calories;
    public TextField Description;
    public Button edit;
    public Button backEditButton;

    public Label nameLabel;
    public Label caloriesLabel;
    public Label descLabel;
    public Button backButton;
    public Button editButton;

    public static boolean backToSearchIngredient;

    Ingredient ig = MenuController.ig;

    public void initialize() {
        nameLabel.setText(ig.getName());
        caloriesLabel.setText(ig.getCalories() + "");
        descLabel.setText(ig.getDesc());
    }

    public void OnBackButton() throws IOException {
        if(backToSearchIngredient == false) {
            FXMLLoader menuView = new FXMLLoader(AddGoodsController.class.getResource("menu-view.fxml"));
            backButton.getScene().setRoot(menuView.load());
        }else{
            FXMLLoader menuView = new FXMLLoader(AddGoodsController.class.getResource("search-view.fxml"));
            backButton.getScene().setRoot(menuView.load());
            backToSearchIngredient = false;
        }
    }

    public void OnBackEditButton() throws IOException {
        FXMLLoader menuView = new FXMLLoader(AddGoodsController.class.getResource("ingredients-details.fxml"));
        backEditButton.getScene().setRoot(menuView.load());
    }

    public void OnEditButton() throws IOException {
        FXMLLoader menuView = new FXMLLoader(AddGoodsController.class.getResource("ingredients-details.fxml"));
        edit.getScene().setRoot(menuView.load());
        if(!Name.getText().isEmpty()) {ig.setName(Name.getText());nameLabel.setText(Name.getText());}
        if(!Calories.getText().isEmpty()){ig.setCalories(Integer.parseInt(Calories.getText()));caloriesLabel.setText(Calories.getText());}
        if(!Description.getText().isEmpty()){ig.setDesc(Description.getText());descLabel.setText(Description.getText());}
    }

    public void OnEditButtonPressed() throws IOException {
        FXMLLoader addIngredientView = new FXMLLoader(MenuController.class.getResource("ingredients-edit.fxml"));
        editButton.getScene().setRoot(addIngredientView.load());
    }

    public void delete() {
        int ID = MenuController.DeleteUID-1;
        if(MenuController.DeleteUID == 1){
            MainApplication.ingList.remove(0);
        }else {
            MainApplication.ingList.remove(ID);
        }
    }
}