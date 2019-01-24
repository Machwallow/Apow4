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

    public final static int WIDTH_GRID = 854, HEIGHT_GRID = 480;
    private static int nbLignes, nbColonnes, nombreVictoire, alignerGagnant;
    private static Joueur[] joueurs = new Joueur[2];
    private static Jeu jeuActuel;
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
    private int jActuel = 0;


    @FXML
    private void initialize() {
        if (jeuActuel == null)
            jeuActuel = new Jeu(nombreVictoire, joueurs[0], joueurs[1]);
        jeuActuel.nouvellePartie(nbColonnes, nbLignes,alignerGagnant);
        genererGrille(nbLignes, nbColonnes);
        System.out.println(Services.getBundle().getLocale());
        labelScoreJ1.setText(Services.getBundle().getString("scoreJ1")+"  "+jeuActuel.getScoreJ1());
        labelScoreJ2.setText(Services.getBundle().getString("scoreJ2")+"  "+jeuActuel.getScoreJ2());

    }

    private void genererGrille(int nbLignes, int nbColonnes){
        mainGrid = new GridPane();
        mainGrid.setGridLinesVisible(true);
        grille = new int[nbColonnes];

        mainGrid.setMinSize(WIDTH_GRID, HEIGHT_GRID);
        mainGrid.setPrefSize(WIDTH_GRID, HEIGHT_GRID);
        mainGrid.setMaxSize(WIDTH_GRID, HEIGHT_GRID);

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
                jetonJActuel.setFitWidth(Services.WIDTH_TOKEN);
                jetonJActuel.setFitHeight(Services.HEIGHT_TOKEN);
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
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/partieGrille.fxml"), Services.getBundle());
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

    public static void setNombreVictoire(int nv){
        nombreVictoire = nv;
    }

    public static void setAlignerGagnant(int ag){
        alignerGagnant = ag;
    }

    public static void setJoueurs(Joueur j1, Joueur j2){
        joueurs[0] = j1;
        joueurs[1] = j2;
    }

}
