package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class accueilControlleur {
    public Button buttonOnline;
    public Button buttonLocal;
    public Button buttonRules;
    public Button buttonQuit;
    public AnchorPane mainPane;

    @FXML
    private void initialize() {

        buttonLocal.setOnMouseClicked(event ->{
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/partieLocal.fxml"));
                mainPane.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        buttonQuit.setOnMouseClicked(event -> {
                Stage stage = (Stage) buttonQuit.getScene().getWindow();
                stage.close();

        });

    }




}
