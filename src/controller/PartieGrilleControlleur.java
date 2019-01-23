package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;


public class PartieGrilleControlleur {

    private static int nbLignes, nbColonnes;
    @FXML
    public AnchorPane mainPane;
    @FXML
    public Label labelScoreJ1;
    @FXML
    public Label labelScoreJ2;
    @FXML
    public BorderPane mainBorder;
    private GridPane mainGrid;
    private int[] grille;
    private Joueur[] joueurs = new Joueur[2];
    private int jActuel = 0;
    private static Jeu jeuActuel;

    @FXML
    private void initialize() {


        joueurs[0] = new Joueur("ressources/red.png");
        joueurs[1] = new Joueur("ressources/black.png");
        if (jeuActuel == null)
            jeuActuel = new Jeu(4, joueurs[0], joueurs[1]);
        jeuActuel.nouvellePartie(nbColonnes, nbLignes,5);
        genererGrille(nbLignes, nbColonnes);
        labelScoreJ1.setText("Score joueur 1:  "+jeuActuel.getScoreJ1());
        labelScoreJ2.setText("Score joueur 2:  "+jeuActuel.getScoreJ2());

    }

    private void genererGrille(int nbLignes, int nbColonnes){
        mainGrid = new GridPane();
        mainGrid.setGridLinesVisible(true);
        grille = new int[nbColonnes];

        mainGrid.setMinSize(854, 480);
        mainGrid.setPrefSize(854, 480);
        mainGrid.setMaxSize(854, 480);

        ColumnConstraints columnConstraint = new ColumnConstraints();
        columnConstraint.setPercentWidth(100/nbColonnes);
        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setPercentHeight(100/nbLignes);

        for (int i = 0; i < nbLignes; i++){
            mainGrid.addRow(i);
            mainGrid.getRowConstraints().add(rowConstraint);
        }

        for (int i = 0; i < nbColonnes; i++){
            grille[i] = nbLignes-1;
            mainGrid.addColumn(i);
            mainGrid.getColumnConstraints().add(columnConstraint);

        }

        for (int i = 0; i < nbLignes; i++)
            for (int j = 0; j < nbColonnes; j++){
                addPane(i,j);
            }

        mainBorder.setCenter(mainGrid);
        mainGrid.setAlignment(Pos.CENTER);
    }

    private void addPane(int indexLigne, int indexColonne){
        StackPane stackPane = new StackPane();
        stackPane.setOnMouseClicked(e -> {
            if(grille[indexColonne]>=0) {
                ImageView jetonJActuel = new ImageView(new Image(joueurs[jActuel].getImg()));
                jetonJActuel.setFitWidth(55);
                jetonJActuel.setFitHeight(36);
                StackPane coupPane = (StackPane) mainGrid.getChildren().get(((grille[indexColonne]) * nbColonnes) +1 + indexColonne);

                System.out.println(((grille[indexColonne]-1) * nbColonnes) +1 + indexColonne);
                System.out.printf("Mouse entered cell [%d, %d]%n", indexColonne, indexLigne);

                coupPane.getChildren().add(jetonJActuel);
                StackPane.setAlignment(coupPane.getChildren().get(0), Pos.CENTER);
                grille[indexColonne]--;
                System.out.println(jActuel);
                int resultatCoup;
                if (jActuel == 0) {
                    resultatCoup = jeuActuel.getPartie().ajouterJeton(1, indexColonne);
                    jActuel++;
                }
                else {
                    resultatCoup = jeuActuel.getPartie().ajouterJeton(-1, indexColonne)+5;
                    jActuel--;
                }
                System.out.println("rc = "+resultatCoup);
                int resultatPartie = 0;
                switch (resultatCoup){
                    case 1: resultatPartie = jeuActuel.victoireJ1(); break;
                    case 2: resultatPartie = -1; break;
                    case 6: resultatPartie = jeuActuel.victoireJ2(); break;
                }
                System.out.println("rp = "+resultatPartie);
                switch (resultatPartie){
                    case -1:
                        System.out.println("-1");
                        nouvellePartie();
                        break;
                    case 3:
                        System.out.println("-1");
                        nouvellePartie();
                        break;
                    case 1:
                        //TODO J1 wins
                        break;
                    case 2:
                        //TODO J2 wins
                        break;
                }

            }
        });
        mainGrid.add(stackPane, indexColonne, indexLigne);
    }

    private void nouvellePartie() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/partieGrille.fxml"));
            mainPane.getChildren().setAll(pane);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void setNbLignes(int nb){
        nbLignes = nb;
    }

    public static void setNbColonnes(int nb){
        nbColonnes = nb;
    }

}
