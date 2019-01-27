package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SetupLocalControlleur {

    @FXML
    public Button buttonLancer, buttonChooserJ1, buttonChooserJ2;
    public ImageView imageJ1, imageJ2;
    public CheckBox checkTriche;
    public TextField textLignes, textColonnes, textPower, textMaxScore;
    public AnchorPane mainPane;
    public Label labelJ1, labelJ2;

    private static Joueur[] joueurs = new Joueur[2];

    @FXML
    private void initialize() {
        setupButtonChooser(0, imageJ1, labelJ1, buttonChooserJ1);
        setupButtonChooser(1, imageJ2, labelJ2, buttonChooserJ2);
        setupButtonLancer();
    }

    private void setupButtonChooser(int numeroJoueur, ImageView iv, Label label, Button bouton){
        bouton.setOnAction(event -> {
            Stage stage = new Stage();
            joueursPickerController.setPlayerToPick(numeroJoueur);
            try {
                AnchorPane root = FXMLLoader.load(getClass().getResource("../vue/joueursPicker.fxml"), Services.getBundle());
                Services.setupNewWindow(stage, root, Services.WIDTH_PLAYERS, Services.HEIGHT_PLAYERS, Services.getBundle().getString("pickAPlayer"));
                stage.getScene().getStylesheets().add(getClass().getResource("../ressources/style.css").toExternalForm());
                stage.showAndWait();
                iv.setImage(new Image(joueurs[numeroJoueur].getImg()));
                label.setText(joueurs[numeroJoueur].getNom());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void setPlayer(int index, Joueur joueur){
        joueurs[index] = joueur;
    }

    private void setupButtonLancer(){
        buttonLancer.setOnMouseClicked(event ->{
            try {
                System.out.println(mainPane.getScene());
                //Définir nbLignes,nbColonnes, alignerGagnat, nbVictoire et les joueurs avant de changer la fenêtre
                PartieGrilleControlleur.setNbLignes(Integer.parseInt(textLignes.getText()));
                PartieGrilleControlleur.setNbColonnes(Integer.parseInt(textColonnes.getText()));
                PartieGrilleControlleur.setAlignerGagnant(Integer.parseInt(textPower.getText()));
                PartieGrilleControlleur.setNombreVictoire(Integer.parseInt(textMaxScore.getText()));
                PartieGrilleControlleur.setJoueurs(joueurs[0], joueurs[1]);

                //Permet de changer la taille de la fenêtre et de la center au milieu de l'écran
                Stage stage = (Stage)mainPane.getScene().getWindow();
                Services.setupFenetre(Services.WIDTH_GAME, Services.HEIGHT_GAME, stage);

                AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/partieGrille.fxml"), Services.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
