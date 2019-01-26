package controller;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class joueursController {


    public TableView<Joueur> tableView;
    public TableColumn<Joueur, String> nameColumn;
    public TableColumn<Joueur, ImageView> imageColumn;
    public TableColumn<Joueur,String> editColumn;
    public TableColumn<Joueur, String> deleteColumn;

    @FXML
    public void initialize() {
        tableFill();
    }

    private void tableFill() {
        tableView.getColumns().setAll(nameColumn, imageColumn, editColumn, deleteColumn);
        tableView.setEditable(false);
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
            return new SimpleObjectProperty<>(new ImageView(new Image(joueur.getImg(), Services.WIDTH_TOKEN, Services.HEIGHT_TOKEN, true, true)));
        });
        editColumn.setCellValueFactory(param -> {
            final Joueur car = param.getValue();
            return new SimpleStringProperty("t1");
        });
        deleteColumn.setCellValueFactory(param -> {
            final Joueur car = param.getValue();
            return new SimpleStringProperty("t2");
        });

        tableView.getItems().setAll(Services.getAllJoueurs());
        tableView.setId("my-table");
    }
}