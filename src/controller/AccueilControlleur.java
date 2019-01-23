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


public class AccueilControlleur {
    public Button buttonOnline;
    public Button buttonLocal;
    public Button buttonRules;
    public Button buttonQuit;
    public AnchorPane mainPane;

    @FXML
    private void initialize() {

        buttonLocal.setOnMouseClicked(event ->{
            try {
                //Définir nbLignes/nbColonnes avant de changer la fenêtre
                PartieGrilleControlleur.setNbLignes(6);
                PartieGrilleControlleur.setNbColonnes(9);

                //Permet de changer la taille de la fenêtre et de la center au milieu de l'écran ensuite
                Stage stage = (Stage)mainPane.getScene().getWindow();
                stage.setWidth(1280);
                stage.setHeight(720);
                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
                stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);

                AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/partieGrille.fxml"));
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
