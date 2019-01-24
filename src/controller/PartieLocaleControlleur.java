package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class PartieLocaleControlleur {


    public Button buttonSplit;
    public Button buttonIA;
    public Button buttonLoad;
    public Button buttonBack;
    public AnchorPane mainPane;
    private static ResourceBundle bundle;

    @FXML
    private void initialize() {
      /*  buttonQuit.setOnMouseClicked(event -> {

        });*/
        try {
            Stage stage = (Stage)mainPane.getScene().getWindow();
            stage.setWidth(800);
            stage.setHeight(800);
            Services.centrerFenetre(stage);

            AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/setupLocal.fxml"));
            ResourceBundle bundle = ResourceBundle.getBundle("bundles.bundle.bundle", Locale.getDefault());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backButton() throws IOException {

//        mainPane = FXMLLoader.load(getClass().getResource("accueil.fxml"),bundle);
    }


}
