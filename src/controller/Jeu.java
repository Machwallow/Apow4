package controller;

import java.util.ArrayList;

public class Jeu {
    private int nombreVictoire;
    private int scoreJ1;
    private int scoreJ2;
    Joueur j1,j2;
    String ImgJ1,ImgJ2;
    ArrayList<Partie> parties;

    public Jeu(int nombreVictoire) {
        scoreJ1=0;
        scoreJ2=0;
        this.nombreVictoire = nombreVictoire;
    }

    public Jeu(int nombreVictoire, Joueur j1, Joueur j2, String imgJ1, String imgJ2) {
        this.nombreVictoire = nombreVictoire;
        this.j1 = j1;
        this.j2 = j2;
        ImgJ1 = imgJ1;
        ImgJ2 = imgJ2;
        this.nombreVictoire = nombreVictoire;
    }

    public void nouvellePartie(int nombreColone, int nombreLigne , int alignerGagnant){
        parties.add(new Partie(nombreColone,nombreLigne,alignerGagnant));
    }
    public boolean jeuFini(){
        return(scoreJ1==nombreVictoire || scoreJ2==nombreVictoire);
    }
}
