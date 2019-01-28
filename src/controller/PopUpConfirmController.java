package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopUpConfirmController {

    public Label label;
    public Button buttonClose, buttonEnd;

    @FXML
    public void initialize(){
        setupButtonMain();
        setButtonBack();
    }


    private void setupButtonMain() {
        buttonEnd.setOnAction(event -> {
            try {
                PartieGrilleControlleur.setConfirm(true);
                Stage stage = (Stage) buttonEnd.getScene().getWindow();
                stage.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void setButtonBack(){
        Services.setCloseWindow(buttonClose);
    }

}
