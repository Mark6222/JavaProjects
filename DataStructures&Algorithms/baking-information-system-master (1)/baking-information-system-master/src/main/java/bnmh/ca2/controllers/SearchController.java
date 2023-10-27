package bnmh.ca2.controllers;

import bnmh.ca2.models.BakedGood;
import bnmh.ca2.models.Ingredient;
import bnmh.ca2.models.Recipe;
import bnmh.ca2.utils.LinkedNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Locale;
import java.util.jar.Attributes;

public class SearchController {

    @FXML
    private Button backButton;
    @FXML
    private TextField searchText;
    @FXML
    private Button searchButton;
    @FXML
    private RadioButton nameRadio;
    @FXML
    private RadioButton descriptionRadio;
    @FXML
    private ListView<String> bakedGoods = new ListView<>();
    @FXML
    private ListView<String> ingredients = new ListView<>();

    public void OnSearchButton() {
        int key = 0;
        System.out.println(searchText.getText());
        char[] temp = searchText.getText().toLowerCase(Locale.ROOT).toCharArray();
        for (char c : temp) {
            key += c;
        }
        key = key % MainApplication.ingHash.length;
        System.out.println(key);
        LinkedNode<BakedGood> bg = MainApplication.goodsHash[key].getHead();
        LinkedNode<Ingredient> ing = MainApplication.ingHash[key].getHead();
        if (bg != null) {
            System.out.println(bg);
            searchBakedGood(key);
        }else if (ing != null){
            System.out.println(ing);
            searchIngredient(key);
        }
    }

    public void searchBakedGood(int key) {
        LinkedNode<BakedGood> bg = MainApplication.goodsHash[key].getHead();
        if (nameRadio.isSelected()) {
            if (bg.getContents().getName().toLowerCase(Locale.ROOT).matches(searchText.getText())) {
                bakedGoods.getItems().add(
                        bg.getContents().getName() + ": " +
                                bg.getContents().getDesc() + ", (Origin: " +
                                bg.getContents().getOrigin() + ")");

                System.out.println(bg.getContents().getName() + ": " +
                        bg.getContents().getDesc() + ", (Origin: " +
                        bg.getContents().getOrigin() + ")");

                bakedGoods.setOnMouseClicked(click -> {
                    if (click.getClickCount() == 2) {
                        if (bakedGoods.getSelectionModel().getSelectedItem() != null) {
                            String uidSelected = bakedGoods.getSelectionModel().getSelectedItem();
                            uidSelected = uidSelected.substring(0, uidSelected.indexOf(":"));
                            System.out.println(uidSelected);
                            LinkedNode<BakedGood> good = MainApplication.list.getHead();
                            int i = 0;
                            while (good != null) {
                                i = i + 1;
                                System.out.println(good.getContents().getName() + ": " + good.getContents().getDesc() + ", (Origin: " + good.getContents().getOrigin() + ")");
                                if (uidSelected.matches(good.getContents().getName())) {
                                    System.out.println(good.getContents().getName());
                                    MenuController.DeleteUID = i;
                                    MenuController.bg = good.getContents();
                                    GoodsDetailsController.backToSearchGood = true;
                                }
                                good = good.getNext();
                            }
                            FXMLLoader caseScene = new FXMLLoader(SearchController.class.getResource("baked-good-details.fxml"));
                            try {
                                bakedGoods.getScene().setRoot(caseScene.load());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
            }
        }
    }

    public void searchIngredient(int key) {
        LinkedNode<Ingredient> ing = MainApplication.ingHash[key].getHead();
        ingredients.getItems().add(
                ing.getContents().getName() + ": " +
                        ing.getContents().getDesc() + ", (Calories: " +
                        ing.getContents().getCalories() + ")");

        System.out.println(ing.getContents().getName() + ": " +
                ing.getContents().getDesc() + ", (Calories: " +
                ing.getContents().getCalories() + ")");

        ingredients.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                if (ingredients.getSelectionModel().getSelectedItem() != null) {
                    String uidSelected = ingredients.getSelectionModel().getSelectedItem();
                    uidSelected = uidSelected.substring(0, uidSelected.indexOf(":"));
                    System.out.println(uidSelected);
                    LinkedNode<Ingredient> ingredient = MainApplication.ingList.getHead();
                    int i = 0;
                    while(ingredient != null) {
                        i = i+1;
                        System.out.println(ingredient.getContents().getName() + ": " + ingredient.getContents().getDesc() + ", (Calories: " + ingredient.getContents().getCalories() + ")");
                        if (uidSelected.matches(ingredient.getContents().getName())){
                            System.out.println(ingredient.getContents().getName());
                            MenuController.DeleteUID = i;
                            MenuController.ig = ingredient.getContents();
                            IngredientsDetailsController.backToSearchIngredient = true;
                        }
                        ingredient = ingredient.getNext();
                    }
                    FXMLLoader caseScene = new FXMLLoader(SearchController.class.getResource("ingredients-details.fxml"));
                    try {
                        bakedGoods.getScene().setRoot(caseScene.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    public void OnBackButton() throws IOException {
        FXMLLoader menuView = new FXMLLoader(AddGoodsController.class.getResource("menu-view.fxml"));
        backButton.getScene().setRoot(menuView.load());
    }
}
