package controller;


import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class PartieGrilleControlleur {

    private static int nbLignes, nbColonnes;
    private GridPane mainGrid;
    @FXML
    public BorderPane mainBorder;
    private int[] grille;
    private Joueur[] joueurs = new Joueur[2];
    private static int jActuel = 0;
    private static Jeu jeuActuel;

    @FXML
    private void initialize() {


        joueurs[0] = new Joueur("ressources/red.png");
        joueurs[1] = new Joueur("ressources/black.png");
        if (jeuActuel == null)
            jeuActuel = new Jeu(5, joueurs[0], joueurs[1]);
        jeuActuel.nouvellePartie(nbLignes,nbColonnes,5);
        genererGrille(nbLignes, nbColonnes);


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

                int resultatPartie;
                switch (resultatCoup){
                    case 1: resultatPartie = jeuActuel.victoireJ1(); break;
                    case 2: resultatPartie = -1; break;
                    case 5: resultatPartie = jeuActuel.victoireJ2(); break;
                    default: resultatPartie = 0; break;
                }

                //switch (resultatPartie){

                //}
            }
        });
        mainGrid.add(stackPane, indexColonne, indexLigne);
    }

    public static void setNbLignes(int nb){
        nbLignes = nb;
    }

    public static void setNbColonnes(int nb){
        nbColonnes = nb;
    }




}
