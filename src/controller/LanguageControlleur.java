package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import vue.Main;

import java.io.IOException;
import java.util.Locale;

public class LanguageControlleur {

    public ImageView ivFR, ivUS;
    public Button buttonBack;
    public AnchorPane mainPane;

    @FXML
    public void initialize(){
        setupivFR();
        setupivUS();
        setupButtonBack();
    }

    private void setupivFR(){
        ivFR.setOnMouseClicked(event -> {
            Services.changeLocale(new Locale("fr", "FR"));
            System.out.println("language changed to FR FR");
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/accueil.fxml"), Services.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Main.stage1.getScene().getStylesheets().add(getClass().getResource("../ressources/style.css").toExternalForm());
    }

    private void setupivUS(){
        ivUS.setOnMouseClicked(event -> {
            Services.changeLocale(new Locale("en", "US"));
            System.out.println("language changed to en US");

            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/accueil.fxml"), Services.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Main.stage1.getScene().getStylesheets().add(getClass().getResource("../ressources/style.css").toExternalForm());

    }

    private void setupButtonBack() {
        Services.setBackToAccueil(buttonBack, mainPane);    }
}