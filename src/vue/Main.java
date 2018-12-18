package vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    private static ResourceBundle bundle;

    public static void changeLocale(Locale l){
        Locale.setDefault(l);
        bundle = ResourceBundle.getBundle("bundles.bundle", Locale.getDefault());
    }



    @Override
    public void start(Stage primaryStage) throws Exception{
        bundle = ResourceBundle.getBundle("bundles.bundle", Locale.getDefault());
        Parent root = FXMLLoader.load(getClass().getResource("accueil.fxml"),bundle);
        changeLocale(new Locale("fr_FR"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
