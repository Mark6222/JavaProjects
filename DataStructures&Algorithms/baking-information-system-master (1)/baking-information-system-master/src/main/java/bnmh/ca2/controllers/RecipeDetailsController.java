package bnmh.ca2.controllers;

import bnmh.ca2.models.BakedGood;
import bnmh.ca2.models.Ingredient;
import bnmh.ca2.models.Recipe;
import bnmh.ca2.models.RecipeItem;
import bnmh.ca2.utils.LinkedNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RecipeDetailsController {

    @FXML
    private ChoiceBox<String> nameChoice;
    @FXML
    private TextField amountText;
    @FXML
    private ChoiceBox<String> typeChoice;
    @FXML
    private Button addRecipeItemButton;
    @FXML
    private ListView<String> recipeItemList;
    @FXML
    private Button backButton;
    Recipe rec = GoodsDetailsController.rec;

    public void initialize() {
        LinkedNode<Ingredient> temp = MainApplication.ingList.getHead();
        while(temp != null) {
            nameChoice.getItems().add(temp.getContents().getName());
            temp = temp.getNext();
        }
        typeChoice.getItems().addAll("g", "ml", "oz", "fl.oz", "tsp", "tbsp");
        repopulate();
    }

    public void repopulate() {

        recipeItemList.getItems().clear();

        RecipeItem temp = rec.getHead();
        while(temp != null) {
            recipeItemList.getItems().add(temp.getAmount() + temp.getType() + " " + temp.getName());
            temp = temp.getNextRecipeItem();
        }

    }

    public void OnAddRecipeItemButton() {

        RecipeItem ri = new RecipeItem(rec, null, nameChoice.getValue(), Integer.parseInt(amountText.getText()), typeChoice.getValue());
        ri.setNextRecipeItem(rec.getHead());
        rec.setHead(ri);

        repopulate();

    }

    public void OnBackButton() throws IOException {
        FXMLLoader goodsDetailsView = new FXMLLoader(GoodsDetailsController.class.getResource("baked-good-details.fxml"));
        backButton.getScene().setRoot(goodsDetailsView.load());
    }

}
