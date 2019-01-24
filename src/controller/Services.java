package controller;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

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
}
