package controller;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class joueursController {

    public TableView<Joueur> tableView;
    public TableColumn<Joueur, String> nameColumn;
    public TableColumn<Joueur, ImageView> imageColumn;
    public TableColumn<Joueur, Button> editColumn;
    public TableColumn<Joueur, Button> deleteColumn;
    public Button buttonCreate, buttonBack, buttonConfirm;
    public AnchorPane mainPane;
    public static ArrayList<Joueur> joueurs;
    public static ArrayList<String> imagesToRemove;

    @FXML
    public void initialize() {
        tableFill();
        setupButtonCreate();
        setupButtonBack();
        setupButtonConfirm();
        imagesToRemove = new ArrayList<>();
    }

    private void setupButtonConfirm() {
        buttonConfirm.setOnMouseClicked(event -> {
            Stage stage = (Stage) mainPane.getScene().getWindow();
            Services.setupFenetre(Services.WIDTH_BASE+10, Services.HEIGHT_BASE+40, stage);
            for (Joueur j:joueurs) {
                File i1 = new File(("src\\tmp\\"+j.getNom()+".png"));
                System.out.println("file 1:"+i1);
                System.out.println("file 1 exists:"+i1.exists());
               /* if (i1.exists()){
                    System.out.println("jimage = "+j.getImg());
                    File i2 = null;
                    try {
                        i2 = Paths.get(new URI(j.getImg())).toFile();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    System.out.println("file2:"+i2);
                    System.out.println("file 2 exists: "+ i2.exists());
                    System.out.println("compare:"+Services.compareImage(i1, i2));
                    if (!Services.compareImage(i1, i2)){
                        j.saveImage();
                        System.out.println(j.getNom());
                        System.out.println(j.getImg());
                    }
                }else{
                    System.out.println("spot1:  "+j.getImg());
                    j.saveImage();
                    System.out.println(j.getNom());
                    System.out.println(j.getImg());
                }/*/

            j.saveImage();
            Services.saveJoueurs(joueurs);
            for (String s : imagesToRemove) {
                File f = new File(s);
                System.out.println(f.delete());
            }
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/partieLocal.fxml"), Services.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }});
    }
    private void setupButtonBack() {
        buttonBack.setOnMouseClicked(event -> {
            Stage stage = (Stage) mainPane.getScene().getWindow();
            Services.setupFenetre(Services.WIDTH_BASE+10, Services.HEIGHT_BASE+40, stage);
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/partieLocal.fxml"), Services.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setupButtonCreate() {
        buttonCreate.setOnAction(event -> {
            Stage stage = new Stage();
            try {
                AnchorPane root = FXMLLoader.load(getClass().getResource("../vue/createJoueur.fxml"), Services.getBundle());
                Services.setupNewWindow(stage, root, Services.WIDTH_BASE, Services.HEIGHT_BASE, Services.getBundle().getString("create"));
                stage.getScene().getStylesheets().add(getClass().getResource("../ressources/style.css").toExternalForm());
                stage.showAndWait();
                tableView.getItems().setAll(joueurs);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void tableFill() {
        System.out.println(new File(".").getAbsolutePath());
        joueurs = Services.getAllJoueurs();
        tableView.getColumns().setAll(nameColumn, imageColumn, editColumn, deleteColumn);
        tableView.setEditable(false);

        //prevent the user from moving the columns around
        tableView.getColumns().addListener(new ListChangeListener() {
            public boolean suspended;
            @Override
            public void onChanged(Change change) {
                change.next();
                if (change.wasReplaced() && !suspended) {
                    this.suspended = true;
                    tableView.getColumns().setAll(nameColumn, imageColumn, editColumn, deleteColumn);
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
            System.out.println("fill  "+joueur.getImg());
            /*try {
                return new SimpleObjectProperty<>(new ImageView(new Image(new FileInputStream(joueur.getImg()), Services.WIDTH_TOKEN, Services.HEIGHT_TOKEN, true, true)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            }*/
            return new SimpleObjectProperty<>(new ImageView(new Image(joueur.getImg(), Services.WIDTH_TOKEN, Services.HEIGHT_TOKEN, true, true)));

        });
        editColumn.setCellFactory(ActionButtonTableCell.forTableColumn(Services.getBundle().getString("edit"), (Joueur j) -> {
            EditJoueurController.setPlayerToEdit(joueurs.indexOf(j));
            Stage stage = new Stage();
            try {
                AnchorPane root = FXMLLoader.load(getClass().getResource("../vue/editJoueur.fxml"), Services.getBundle());
                Services.setupNewWindow(stage, root, Services.WIDTH_BASE, Services.HEIGHT_BASE, Services.getBundle().getString("playerEdit"));
                stage.getScene().getStylesheets().add(getClass().getResource("../ressources/style.css").toExternalForm());
                stage.showAndWait();
                tableView.getItems().removeAll();
                tableView.getItems().setAll(joueurs);
            } catch (IOException e) {
                e.printStackTrace();
            }
            tableView.getItems().removeAll();
            tableView.getItems().setAll(joueurs);
            return j;
        }));
        deleteColumn.setCellFactory(ActionButtonTableCell.forTableColumn(Services.getBundle().getString("delete"), (Joueur j) -> {
            tableView.getItems().remove(j);
            joueurs.remove(j);
            System.out.println(joueurs);
            return j;
        }));

        tableView.getItems().setAll(joueurs);
        tableView.setId("my-table");
    }

    public static void editPlayer(int index, String name, String image){
        System.out.println(joueurs);
        joueurs.get(index).setNom(name);
        joueurs.get(index).setImg(image);
        System.out.println(joueurs);
    }
}