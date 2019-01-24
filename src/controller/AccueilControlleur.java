package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class AccueilControlleur {
    public Button buttonOnline;
    public Button buttonLocal;
    public Button buttonRules;
    public Button buttonQuit;
    public AnchorPane mainPane;
    private static ResourceBundle bundle;

    @FXML
    private void initialize() {
        buttonLocal.setOnMouseClicked(event ->{
            Stage stage = (Stage)mainPane.getScene().getWindow();
            stage.setWidth(800);
            stage.setHeight(800);
            Services.centrerFenetre(stage);

            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/setupLocal.fxml"));
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
