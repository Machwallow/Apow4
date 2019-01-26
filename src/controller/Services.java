package controller;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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

    public static void setupFenetre(int x, int y, Stage stage){
        stage.setWidth(x);
        stage.setHeight(y);
        centrerFenetre(stage);
    }

    public static void centrerFenetre(Stage stage){
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }

    public static void saveImage(Joueur joueur){
        try {
            BufferedImage bi = ImageIO.read(new File(joueur.getImg().substring(6)));
            File outputfile = new File("src\\tmp\\"+joueur.getNom()+".png");
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Joueur> getAllJoueurs(){
        ArrayList<Joueur> listJoueurs = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("src\\tmp\\saveJoueurs.ser");
            ObjectInputStream ois=new ObjectInputStream(fis);
            listJoueurs=(ArrayList<Joueur>)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
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
}
