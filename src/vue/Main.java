package vue;

import controller.IA;
import controller.Partie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        primaryStage.setTitle("Apow4");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setResizable(false);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();



    }


    public static void main(String[] args) {

        Partie partie=new Partie(3,19,3);
        System.out.println(partie+"\n");
        IA bot=new IA(9);
        int equipe=1,i=0,ajoutJeton;
        ajoutJeton=partie.ajouterJeton(equipe,bot.choisirCoup(partie, equipe));
        while(i<99999 && ajoutJeton==0){
            System.out.println("ajoutJeton  "+ajoutJeton);
            i=i+1;
            equipe=equipe*-1;
            System.out.println("LALALALLALALALLALALALALLALALALA");
            System.out.println(partie+"\n");
            ajoutJeton=partie.ajouterJeton(equipe,bot.choisirCoup(partie, equipe));
        }
        System.out.println("LALALALLALALALLALALALALLALALALA");
        System.out.println(partie+"\n");



        launch(args);
    }
}
