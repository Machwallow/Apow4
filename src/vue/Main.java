package vue;

import controller.Services;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage1;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Services.setupBundle();
        stage1 = primaryStage;
        AnchorPane root = FXMLLoader.load(getClass().getResource("accueil.fxml"), Services.getBundle());
        primaryStage.setTitle("Apow(4)");
        primaryStage.setScene(new Scene(root, Services.WIDTH_BASE, Services.HEIGHT_BASE));
        primaryStage.getScene().getStylesheets().add(getClass().getResource("../ressources/style.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("ressources/logo.png"));
        primaryStage.show();



    }


    public static void main(String[] args) {

       /* Partie partie=new Partie(3,19,3);
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
        System.out.println(partie+"\n");*/



        launch(args);
    }
}
