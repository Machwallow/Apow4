package controller;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Stack;


public class PartieGrilleControlleur {

    private static int nbLignes, nbColonnes;
    public GridPane mainGrid;
    @FXML
    public BorderPane mainBorder;
    private int[] grille;
    private Joueur[] joueurs = new Joueur[2];


    @FXML
    private void initialize() {


        genererGrille(nbLignes, nbColonnes);


    }

    private void genererGrille(int nbLignes, int nbColonnes){
        mainGrid = new GridPane();
        mainGrid.setGridLinesVisible(true);
        grille = new int[nbColonnes];


        //images 61x40 Maxi

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
            grille[i] = nbColonnes-1;
            mainGrid.addColumn(i);
            mainGrid.getColumnConstraints().add(columnConstraint);

        }
       // ImageView ivj1 = new ImageView(new Image("ressources/black.png"));
        ImageView ivj2 = new ImageView(new Image("ressources/red.png"));



        for (int i = 0; i < nbColonnes; i++)
            for (int j = 0; j < nbLignes; j++){
                /*
                ImageView ivj1 = new ImageView(new Image("ressources/black.png"));
                ivj1.setFitWidth(55);
                ivj1.setFitHeight(36);
                mainGrid.add(ivj1, i, j);
                GridPane.setHalignment(ivj1, HPos.CENTER);
                GridPane.setValignment(ivj1, VPos.CENTER);
                */
                addPane(i,j);
            }



        mainBorder.setCenter(mainGrid);
        mainGrid.setAlignment(Pos.CENTER);

    }

    public void addPane(int indexColonne, int indexLigne){
        StackPane stackPane = new StackPane();
       /*
        pane.setOnMouseEntered(e -> {
            System.out.printf("Mouse enetered cell [%d, %d]%n", indexColonne, indexLigne);
        });
        */
        stackPane.setOnMouseClicked(e -> {
            if(grille[indexColonne]>=0) {
                ObservableList<Node> children = mainGrid.getChildren();
                Node result = null;
                for (Node node : children) {
                    if(GridPane.getRowIndex(node) == grille[indexColonne] && GridPane.getColumnIndex(node) == indexColonne) {
                        result = (StackPane)node;
                        break;
                    }
                }
                StackPane coupPane = (StackPane) result;
                coupPane.getChildren().add(new ImageView(new Image("ressources/red.png")));
                // System.out.printf(grille[indexColonne]+"  "+nbColonnes+"  "+indexColonne);

            //   StackPane coupPane = (StackPane) mainGrid.getChildren().get(((grille[indexColonne]) * nbColonnes) - indexColonne);
                System.out.printf("Mouse enetered cell [%d, %d]%n", indexColonne, indexLigne);

             //   coupPane.getChildren().add(new ImageView(new Image("ressources/red.png")));
                StackPane.setAlignment(coupPane.getChildren().get(0), Pos.CENTER);
                grille[indexColonne]--;
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
