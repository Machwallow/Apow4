package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class WinnerController {

    public Label label;
    public Button button;
    private static Joueur winner;

    @FXML
    public void initialize(){
        label.setText(winner.getNom()+" "+label.getText());
        setupButtonMain();
    }

    private void setupButtonMain() {
        Services.setCloseWindow(button);
    }

    public static void setWinner(Joueur winner) {
        WinnerController.winner = winner;
    }
}
