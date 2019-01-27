package controller;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Services {

    private static ResourceBundle bundle;

    public final static int WIDTH_BASE = 300, HEIGHT_BASE = 300;
    public final static int WIDTH_SETUP = 800, HEIGHT_SETUP = 800;
    public final static int WIDTH_GAME = 1280, HEIGHT_GAME = 720;
    public final static int WIDTH_TOKEN = 55, HEIGHT_TOKEN = 36;
    public final static int WIDTH_POP_UP = 300, HEIGHT_POP_UP = 150;


    public static void setupBundle(){
        bundle = ResourceBundle.getBundle("bundles.bundle", Locale.getDefault());
    }

    public static void changeLocale(Locale l){
        Locale.setDefault(l);
        bundle = ResourceBundle.getBundle("bundles.bundle", Locale.getDefault());

    }

    public static ResourceBundle getBundle(){
        return bundle;
    }

    public static void setupFenetre(int width, int height, Stage stage){
        stage.setWidth(width);
        stage.setHeight(height);
        centrerFenetre(stage);
    }

    public static void centrerFenetre(Stage stage){
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY(((screenBounds.getHeight() - stage.getHeight()) / 2.75));
    }

    public static ArrayList<Joueur> getAllJoueurs(){
        ArrayList<Joueur> listJoueurs = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("src\\tmp\\saveJoueurs.ser");
            ObjectInputStream ois=new ObjectInputStream(fis);
            listJoueurs=(ArrayList<Joueur>)ois.readObject();
        } catch (Exception e) {
        }
        return listJoueurs;
    }

    public static void saveJoueurs(ArrayList<Joueur> joueurs){
        ///serialization
        try {
            File fichier = new File("src\\tmp\\saveJoueurs.ser");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
            oos.writeObject(joueurs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FileChooser imageChooser(String name){
        FileChooser temp = new FileChooser();
        temp.setTitle(name);
        temp.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        return temp;
    }

    public static void setCloseWindow(Button button){
        button.setOnAction(event -> {
            try {
                Stage stage = (Stage) button.getScene().getWindow();
                stage.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public static void setupNewWindow(Stage stage, AnchorPane mainPane, int width, int height, String title){
        stage.setTitle(title);
        stage.setScene(new Scene(mainPane, width, height));
        stage.getIcons().add(new Image("ressources/logo.png"));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    public static void setBackToAccueil(Button button, AnchorPane mainPane){
        button.setOnMouseClicked(event -> {
            try {
                AnchorPane pane = FXMLLoader.load(Services.class.getResource("../vue/accueil.fxml"), Services.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    /*
    public static boolean compareImage(File fileA, File fileB) {
        try {
            // take buffer data from both image files //

            System.out.println("compare fileB"+fileB);
            FileInputStream inputFileA = new FileInputStream(fileA);
            FileInputStream inputFileB = new FileInputStream(fileB);
            BufferedImage biA = ImageIO.read(inputFileA);
            DataBuffer dbA = biA.getData().getDataBuffer();
            int sizeA = dbA.getSize();
            BufferedImage biB = ImageIO.read(inputFileB);
            DataBuffer dbB = biB.getData().getDataBuffer();
            int sizeB = dbB.getSize();
            // compare data-buffer objects //
            if(sizeA == sizeB) {
                for(int i=0; i<sizeA; i++)
                    if(dbA.getElem(i) != dbB.getElem(i))
                        return false;
                return true;
            } else
                return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to compare image files ...");
            return  false;
        }
    }
    */
}
