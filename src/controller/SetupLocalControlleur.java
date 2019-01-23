package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class SetupLocalControlleur {

    @FXML
    public Button buttonLancer, buttonChooserJ1, buttonChooserJ2;
    public ImageView imageJ1, imageJ2;
    public CheckBox checkTriche;
    public TextField textLignes, textColonnes, textPower, textMaxScore;
    public AnchorPane mainPane;

    private static Joueur[] joueurs = new Joueur[2];

    @FXML
    private void initialize() {

        setupButtonChooser(0, imageJ1, buttonChooserJ1);
        setupButtonChooser(1, imageJ2, buttonChooserJ2);
        setupButtonLancer();
    }



    private void setupButtonChooser(int numeroJoueur, ImageView iv, Button bouton){
        bouton.setOnMouseClicked(event ->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choisir l'image du joueur "+numeroJoueur);
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            File fichier = fileChooser.showOpenDialog(mainPane.getScene().getWindow());
            if (fichier != null) {
                try {
                    iv.setImage(new Image(fichier.toURI().toURL().toExternalForm()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                joueurs[numeroJoueur] = new Joueur(fichier.getAbsolutePath());
            }
        });
    }

    private void setupButtonLancer(){
        buttonLancer.setOnMouseClicked(event ->{
            try {
                //Définir nbLignes,nbColonnes, alignerGagnat, nbVictoire et les joueurs avant de changer la fenêtre
                PartieGrilleControlleur.setNbLignes(Integer.parseInt(textLignes.getText()));
                PartieGrilleControlleur.setNbColonnes(Integer.parseInt(textColonnes.getText()));
                PartieGrilleControlleur.setAlignerGagnant(Integer.parseInt(textPower.getText()));
                PartieGrilleControlleur.setNombreVictoire(Integer.parseInt(textMaxScore.getText()));
                PartieGrilleControlleur.setJoueurs(joueurs[0], joueurs[1]);

                //Permet de changer la taille de la fenêtre et de la center au milieu de l'écran
                Stage stage = (Stage)mainPane.getScene().getWindow();
                stage.setWidth(1280);
                stage.setHeight(720);
                Services.centrerFenetre(stage);

                AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/partieGrille.fxml"));
                mainPane.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
