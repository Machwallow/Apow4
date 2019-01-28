package controller;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class joueursPickerController {

    public TableView<Joueur> tableView;
    public TableColumn<Joueur, String> nameColumn;
    public TableColumn<Joueur, ImageView> imageColumn;
    public TableColumn<Joueur, Button> pickColumn;
    public Button buttonBack;
    public AnchorPane mainPane;
    public static ArrayList<Joueur> joueurs;
    private static int playerToPick;
    private static boolean ia;

    @FXML
    public void initialize() {
        tableFill();
        setupButtonBack();
    }

    private void setupButtonBack() {
        Services.setCloseWindow(buttonBack);
    }

    private void tableFill() {
        joueurs = Services.getAllJoueurs();
        tableView.getColumns().setAll(nameColumn, imageColumn, pickColumn);
        tableView.setEditable(false);

        //prevents the user from moving the columns around
        tableView.getColumns().addListener(new ListChangeListener() {
            public boolean suspended;
            @Override
            public void onChanged(Change change) {
                change.next();
                if (change.wasReplaced() && !suspended) {
                    this.suspended = true;
                    tableView.getColumns().setAll(nameColumn, imageColumn, pickColumn);
                    this.suspended = false;
                }
            }
        });

        nameColumn.setCellValueFactory(param -> {
            final Joueur joueur = param.getValue();
            return new SimpleStringProperty(joueur.getNom());
        });
        imageColumn.setCellValueFactory(param -> {
            Joueur joueur = param.getValue();
            return new SimpleObjectProperty<>(new ImageView(new Image(joueur.getImg(), Services.WIDTH_TOKEN, Services.HEIGHT_TOKEN, true, true)));

        });
        pickColumn.setCellFactory(ActionButtonTableCell.forTableColumn(Services.getBundle().getString("pick"), (Joueur j) -> {
            System.out.println(j);
            System.out.println(playerToPick);
            if (ia)
                SetupLocalIAControlleur.setPlayer(playerToPick, j);
            else
                SetupLocalControlleur.setPlayer(playerToPick, j);

            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.close();
            return j;
        }));

        tableView.getItems().setAll(joueurs);
        tableView.setId("my-table");
    }

    public static void setPlayerToPick(int playerNumber){
        playerToPick = playerNumber;
    }
    public static void setIA(boolean ia){
        joueursPickerController.ia = ia;
    }
}