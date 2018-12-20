package controller;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class accueilControlleur {
    public Button buttonOnline;
    public Button buttonLocal;
    public Button buttonRules;
    public Button buttonQuit;

    @FXML
    private void initialize() {
        buttonQuit.setOnMouseClicked(event -> {
                Stage stage = (Stage) buttonQuit.getScene().getWindow();
                stage.close();

        });

    }




}
