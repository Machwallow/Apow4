package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RulesController {

    public Button buttonBack;
    public AnchorPane mainPane;

    @FXML
    public void initialize(){
        Services.setBackToAccueil(buttonBack, mainPane);

    }

}
