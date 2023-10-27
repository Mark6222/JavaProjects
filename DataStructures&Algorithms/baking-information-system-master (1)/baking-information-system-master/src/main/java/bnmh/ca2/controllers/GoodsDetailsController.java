package bnmh.ca2.controllers;

import bnmh.ca2.models.BakedGood;
import bnmh.ca2.models.Recipe;
import bnmh.ca2.utils.GenericLinkedList;
import bnmh.ca2.utils.LinkedNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;

public class GoodsDetailsController {

    @FXML
    private Label nameLabel;
    @FXML
    private Label originLabel;
    @FXML
    private Label descLabel;
    @FXML
    private ImageView imgView;
    @FXML
    private ListView<String> recipeList = new ListView<>();
    @FXML
    private Button addRecipeButton;
    @FXML
    private Button backButton;

    public TextField Name;
    public TextField Origin = null;
    public TextField Description = null;
    public Button edit;
    public Button backEditButton;
    public ImageView imageView = null;
    private String filepath = null;
    public Button editButton;
    public Button fileButton;

    public static boolean backToSearchGood;

    BakedGood bg = MenuController.bg;
    public static Recipe rec;

    public void initialize() {
        nameLabel.setText(bg.getName());
        originLabel.setText(bg.getOrigin());
        descLabel.setText(bg.getDesc());
        try {
            InputStream stream = new FileInputStream(bg.getFilepath());
            Image image = new Image(stream);
            imgView.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        repopulate();

        recipeList.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                if (recipeList.getSelectionModel().getSelectedItem() != null) {
                    String uidSelected = recipeList.getSelectionModel().getSelectedItem();
                    uidSelected = uidSelected.substring(0, uidSelected.indexOf(":"));
                    System.out.println(uidSelected);
                    Recipe temp = bg.getHead();
                    for (int i = 0; i < Integer.parseInt(uidSelected) - 1; i++) {
                        temp = temp.getNextRecipe();
                    }
                    System.out.println(temp.getName());
                    rec = temp;
                    FXMLLoader caseScene = new FXMLLoader(MenuController.class.getResource("recipe-details.fxml"));
                    try {
                        recipeList.getScene().setRoot(caseScene.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }

    public void repopulate() {
        recipeList.getItems().clear();
        Recipe temp = MenuController.bg.getHead();
        int i = 1;
        while (temp != null) {
            recipeList.getItems().add(i + ": " +
                    temp.getName() + " (" +
                    temp.getDesc() + ")");
            System.out.println(temp.getName() + " (" +
                    temp.getDesc() + ")");
            i++;
            temp = temp.getNextRecipe();
        }
    }

    public void OnAddRecipeButton() throws IOException {
        FXMLLoader addRecipeView = new FXMLLoader(GoodsDetailsController.class.getResource("add-recipe-view.fxml"));
        addRecipeButton.getScene().setRoot(addRecipeView.load());
    }

    public void OnFileButton() throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.webp"));
        File selectedFile = fileChooser.showOpenDialog(fileButton.getScene().getWindow());
        if (selectedFile != null) {
            filepath = selectedFile.getAbsolutePath();
        }
        InputStream stream = new FileInputStream(filepath);
        Image image = new Image(stream);
        imageView.setImage(image);
    }

    public void OnBackButton() throws IOException {
        if(backToSearchGood == false) {
            FXMLLoader menuView = new FXMLLoader(AddGoodsController.class.getResource("menu-view.fxml"));
            backButton.getScene().setRoot(menuView.load());
        }else{
            FXMLLoader menuView = new FXMLLoader(AddGoodsController.class.getResource("search-view.fxml"));
            backButton.getScene().setRoot(menuView.load());
            backToSearchGood = false;
        }
    }

    public void OnBackEditButton() throws IOException {
        FXMLLoader menuView = new FXMLLoader(AddGoodsController.class.getResource("baked-good-details.fxml"));
        backEditButton.getScene().setRoot(menuView.load());
    }

    public void OnEditButton() throws IOException {
        FXMLLoader menuView = new FXMLLoader(AddGoodsController.class.getResource("baked-good-details.fxml"));
        edit.getScene().setRoot(menuView.load());
        if (!Name.getText().isEmpty()) {
            bg.setName(Name.getText());
            nameLabel.setText(null);
        }
        if (!Origin.getText().isEmpty()) {
            originLabel.setText(Origin.getText());
            bg.setOrigin(Origin.getText());
        }
        if (!Description.getText().isEmpty()) {
            bg.setDesc(Description.getText());
            descLabel.setText(Description.getText());
        }
        if (filepath != null) {
            bg.setFilepath(filepath);
            imgView = imageView;
            try {
                InputStream stream = new FileInputStream(filepath);
                Image image = new Image(stream);
                imgView.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        refresh();

    }

    public void OnEditButtonPressed() throws IOException {
        FXMLLoader addIngredientView = new FXMLLoader(MenuController.class.getResource("goods-edit.fxml"));
        editButton.getScene().setRoot(addIngredientView.load());
    }

    public void refresh() {
        initialize();
    }

    public void delete() {
        int ID = MenuController.DeleteUID-1;
        if(MenuController.DeleteUID == 1){
            MainApplication.list.remove(0);
        }else {
            MainApplication.list.remove(ID);
        }
    }
}