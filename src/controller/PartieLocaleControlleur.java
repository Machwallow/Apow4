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

    @FXML
    private void initialize() {
      /*  buttonQuit.setOnMouseClicked(event -> {

        });*/

    }

    public void backButton() throws IOException {


        ResourceBundle bundle = ResourceBundle.getBundle("bundles.bundle", Locale.getDefault());

        Scene scene = buttonBack.getScene();
      //  Window window = scene.getWindow();
     //   Stage stage = (Stage) window;

        mainPane = FXMLLoader.load(getClass().getResource("accueil.fxml"),bundle);
    }


}
