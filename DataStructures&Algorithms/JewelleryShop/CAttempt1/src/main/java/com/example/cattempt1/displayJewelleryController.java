package com.example.cattempt1;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.ChoiceBox;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;

public class displayJewelleryController {
    public TextField JewelleryID;
    public TextField description;
    public TextField type;
    public ChoiceBox<String> Gender;
    public TextField URL;
    public TextField price;
    public int jewelleryNum = 100;
    public String invalid = "";


    public void addJewellery() {
        jewelleryNum = jewelleryNum + Integer.parseInt(JewelleryID.getText());
        displayCase temp = JewelleryShop.head;
        while (temp.ID != JewelleryShop.caseID) {
            temp = temp.next;
        }
        if(temp.ID == JewelleryShop.caseID) {
            displayTray temp2 = temp.trayHead;
            while (temp2.getID() != JewelleryShop.trayID) {
                temp2 = temp2.next;
            }
            if (temp2.getID() == JewelleryShop.trayID) {
                if (temp2.jewelleryHead == null) {
                    temp2.jewelleryHead = new displayJewellery(null, jewelleryNum, description.getText(), type.getText(), Gender.getValue(), URL.getText(), Double.parseDouble(price.getText()));
                    System.out.println(temp2.jewelleryHead);
                    if(temp2.jewelleryHead == null){
                        invalid = "invalid field entered";
                    }
                } else {
                    displayJewellery jewellery = temp2.jewelleryHead;
                    while (jewellery.next != null) {
                        jewellery = jewellery.next;
                    }
                    jewellery.next = new displayJewellery(null, jewelleryNum, description.getText(), type.getText(), Gender.getValue(), URL.getText(), Double.parseDouble(price.getText()));
                    System.out.println(jewellery.next);
                    if(jewellery.next == null){
                        invalid = "invalid field entered";
                    }
                }
            }
        }
    }


    public void setOnButtonClickedJewelleryList(ActionEvent event) throws IOException {
        addJewellery();
        Parent jewelleryStore = FXMLLoader.load(getClass().getResource("JewelleryList.fxml"));
        Scene jewelleryStoreScene = new Scene(jewelleryStore);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(jewelleryStoreScene);
        stage.show();
    }

    @FXML
    private void setOnButtonClickedBack(ActionEvent event) throws IOException {
        Parent addCase = FXMLLoader.load(getClass().getResource("JewelleryList.fxml"));
        Scene addCaseScene = new Scene(addCase);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(addCaseScene);
        stage.show();
    }

    public void initialize() {
        Gender.getItems().addAll("Male", "Female", "Unisex");
    }
}
