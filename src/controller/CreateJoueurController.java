package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

public class CreateJoueurController {

    public Button buttonChangeImage, buttonConfirm, buttonClose;
    public ImageView ivImage;
    public TextField textFieldName;
    public AnchorPane mainPane;
    private String nom, image;

    @FXML
    public void initialize(){
        basicSetup();
    }

    private void basicSetup() {
        setupButtonChangeImage();
        setupButtonConfirm();
        setupButtonClose();
    }


    private void setupButtonChangeImage() {
        buttonChangeImage.setOnMouseClicked(event ->{
            FileChooser fileChooser = Services.imageChooser("Choisir l'image du joueur");
            File fichier = fileChooser.showOpenDialog(mainPane.getScene().getWindow());
            if (fichier != null) {
                image = fichier.toURI().toString();
                ivImage.setImage(new Image(image));
            }
        });
    }

    private void setupButtonConfirm() {
        buttonConfirm.setOnAction(event -> {
            nom = textFieldName.getText();
            System.out.println(nom);
            System.out.println(image);
            if (nom != null && nom != "" && image != null && image != "") {
                Joueur joueurTemp = new Joueur(nom, image);
                joueurTemp.saveImage();
                joueursController.joueurs.add(joueurTemp);

                Stage stage = (Stage) buttonClose.getScene().getWindow();
                stage.close();
            }
        });
    }

    private void setupButtonClose() {
        Services.setCloseWindow(buttonClose);
    }
}