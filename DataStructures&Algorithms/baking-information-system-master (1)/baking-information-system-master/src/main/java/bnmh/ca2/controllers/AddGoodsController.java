package bnmh.ca2.controllers;

import bnmh.ca2.models.BakedGood;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class AddGoodsController {

    @FXML
    private Button backButton;
    @FXML
    private TextField nameText;
    @FXML
    private TextField originText;
    @FXML
    private TextArea descText;
    @FXML
    private Button fileButton;
    @FXML
    private Label fileLabel;
    @FXML
    private Button addBakedGoodButton;
    @FXML
    private ImageView imageView;

    private String filepath;

    private int hash(BakedGood bg) {
        char[] temp = bg.getName().toLowerCase(Locale.ROOT).toCharArray();
        int key = 0;
        for(char c : temp) {
            key += c;
        }
        return key%MainApplication.ingHash.length;
    }

    public void OnFileButton() throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.webp"));
        File selectedFile = fileChooser.showOpenDialog(fileButton.getScene().getWindow());
        if(selectedFile != null) {
            fileLabel.setText(selectedFile.getName());
            filepath = selectedFile.getAbsolutePath();
        }
        InputStream stream = new FileInputStream(filepath);
        Image image = new Image(stream);
        imageView.setImage(image);

    }

    public void OnAddBakedGoodButton() throws IOException {

        if(nameText.getText() != null && originText.getText() != null && descText.getText() != null && filepath != null) {

            BakedGood bg = new BakedGood(null, null, nameText.getText(), originText.getText(), descText.getText(), filepath);
            MainApplication.list.add(bg);
            int i = hash(bg);
            System.out.println("\nAdding Ingredient to Hash Table goodsHash:\nHash No: " + i);
            MainApplication.goodsHash[i].add(bg);
            System.out.print("Ingredient ID: ");
            MainApplication.goodsHash[i].list();
            System.out.println("\n");

            OnBackButton();

        }

    }

    public void OnBackButton() throws IOException {
        FXMLLoader menuView = new FXMLLoader(AddGoodsController.class.getResource("menu-view.fxml"));
        backButton.getScene().setRoot(menuView.load());
    }

}
