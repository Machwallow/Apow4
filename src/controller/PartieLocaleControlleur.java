package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import vue.Main;

public class PartieLocaleControlleur {

    public Button buttonSplit;
    public Button buttonIA;
    public Button buttonPlayers;
    public Button buttonBack;
    public AnchorPane mainPane;
    public Label title;

    @FXML
    private void initialize() {
        setupButtonSplit();
        setupButtonBack();
        setupButtonPlayers();
        title.setFont(new Font("Trebuchet MS Italic", 36.0));
    }

    private void setupButtonSplit(){
        buttonSplit.setOnMouseClicked(event -> {
            Stage stage = Main.stage1;
            Services.setupFenetre(Services.WIDTH_SETUP, Services.HEIGHT_SETUP, stage);
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/setupLocal.fxml"), Services.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setupButtonIA(){
        buttonIA.setOnMouseClicked(event -> {
            //TODO Lancement partie IA
        });
    }

    private void setupButtonPlayers(){
        buttonPlayers.setOnMouseClicked(event -> {
            Stage stage = (Stage) mainPane.getScene().getWindow();
            Services.setupFenetre(Services.WIDTH_PLAYERS,Services.HEIGHT_PLAYERS,stage);
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/joueurs.fxml"), Services.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setupButtonBack(){
        Services.setBackToAccueil(buttonBack, mainPane);
    }


}
