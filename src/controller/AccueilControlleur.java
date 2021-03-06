package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;


public class AccueilControlleur {
    public Button buttonLocal;
    public Button buttonLanguage;
    public Button buttonRules;
    public Button buttonQuit;
    public AnchorPane mainPane;
    public Label title;

    @FXML
    private void initialize() {
        setupButtonLocal();
        setupButtonLanguage();
        setupButtonQuit();
        setupButtonRules();
        title.setFont(new Font("Trebuchet MS Italic", 36.0));
    }

    private void setupButtonLocal(){
        buttonLocal.setOnAction(event ->{
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/partieLocal.fxml"), Services.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setupButtonRules() {
        buttonRules.setOnAction(event -> {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/rules.fxml"), Services.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setupButtonLanguage(){
        buttonLanguage.setOnAction(event ->{
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/language.fxml"), Services.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setupButtonQuit(){
        Services.setCloseWindow(buttonQuit);
    }


}
