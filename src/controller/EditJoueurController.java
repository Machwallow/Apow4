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

public class EditJoueurController {
    private static int playerToEdit;
    private Joueur joueurTemp;
    public Button buttonChangeImage, buttonConfirm, buttonClose;
    public ImageView ivImage;
    public TextField textFieldName;
    public AnchorPane mainPane;
    private String prevName;

    @FXML
    public void initialize(){
        basicSetup();
    }

    private void basicSetup() {
        joueurTemp = new Joueur(joueursController.joueurs.get(playerToEdit).getNom(), joueursController.joueurs.get(playerToEdit).getImg());
        textFieldName.setText(joueurTemp.getNom());
        prevName = joueurTemp.getNom();
       /* try {
            ivImage.setImage(new Image(new FileInputStream(playerToEdit.getImg())));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        ivImage.setImage(new Image(joueursController.joueurs.get(playerToEdit).getImg()));

        setupButtonChangeImage();
        setupButtonConfirm();
        setupButtonClose();
    }


    private void setupButtonChangeImage() {
        buttonChangeImage.setOnMouseClicked(event ->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choisir l'image du joueur");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            File fichier = fileChooser.showOpenDialog(mainPane.getScene().getWindow());
            if (fichier != null) {
                ivImage.setImage(new Image(fichier.toURI().toString()));
                joueurTemp.setImg(fichier.toURI().toString());
            }
        });
    }

    private void setupButtonConfirm() {
        buttonConfirm.setOnAction(event -> {
            joueursController.joueurs.get(playerToEdit).saveImageEdit(prevName, joueurTemp.getImg());
            joueursController.editPlayer(playerToEdit, textFieldName.getText(),joueurTemp.getImg());
            if (prevName != joueursController.joueurs.get(playerToEdit).getNom()) {
                System.out.println(prevName);
                joueursController.imagesToRemove.add("src\\tmp\\"+prevName+".png");
            }
            try {
                Stage stage = (Stage) buttonClose.getScene().getWindow();
                stage.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void setupButtonClose() {
        Services.setCloseWindow(buttonClose);
    }


    public static void setPlayerToEdit(int joueur){
        playerToEdit = joueur;
    }
}
