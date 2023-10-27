package bnmh.ca2.controllers;

import bnmh.ca2.models.BakedGood;
import bnmh.ca2.models.Ingredient;
import bnmh.ca2.utils.GenericLinkedList;
import bnmh.ca2.utils.LinkedNode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainApplication extends Application {



    public static GenericLinkedList<BakedGood> list = new GenericLinkedList<>();
    public static GenericLinkedList<Ingredient> ingList = new GenericLinkedList<>();
    public static GenericLinkedList<BakedGood>[] goodsHash = new GenericLinkedList[10];
    public static GenericLinkedList<Ingredient>[] ingHash = new GenericLinkedList[10];

    //loads main menu
    @Override
    public void start(Stage stage) throws IOException {
        for(int i = 0; i < goodsHash.length; i++) {
            goodsHash[i] = new GenericLinkedList<>();
        }
        for(int i = 0; i < ingHash.length; i++) {
            ingHash[i] = new GenericLinkedList<>();
        }
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(MenuController.goodsFilename()));
            ObjectInputStream in2 = new ObjectInputStream(new FileInputStream(MenuController.ingredientsFilename()));
            ObjectInputStream in3 = new ObjectInputStream(new FileInputStream(MenuController.goodsHashFilename()));
            ObjectInputStream in4 = new ObjectInputStream(new FileInputStream(MenuController.ingHashFilename()));
            list.setHead((LinkedNode<BakedGood>) in.readObject());
            ingList.setHead((LinkedNode<Ingredient>) in2.readObject());
            goodsHash = (GenericLinkedList<BakedGood>[]) in3.readObject();
            ingHash = (GenericLinkedList<Ingredient>[]) in4.readObject();
            in.close();
            in2.close();
            in3.close();
            in4.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Baking Info");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }



}