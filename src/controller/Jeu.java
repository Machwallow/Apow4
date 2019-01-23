package controller;

import java.util.ArrayList;

public class Jeu {
    private int nombreVictoire;
    private int scoreJ1;
    private int scoreJ2;
    Joueur j1,j2;
    ArrayList<Partie> parties;

    public Jeu(int nombreVictoire) {
        scoreJ1=0;
        scoreJ2=0;
        this.nombreVictoire = nombreVictoire;
    }

    public Jeu(int nombreVictoire, Joueur j1, Joueur j2) {
        this.nombreVictoire = nombreVictoire;
        this.j1 = j1;
        this.j2 = j2;
        scoreJ1=0;
        scoreJ2=0;
    }

    public void nouvellePartie(int nombreColone, int nombreLigne , int alignerGagnant){
        parties.add(new Partie(nombreColone,nombreLigne,alignerGagnant));
    }
    public boolean jeuFini(){
        return(scoreJ1==nombreVictoire || scoreJ2==nombreVictoire);
    }

    public Partie getPartie(){
        return parties.get(parties.size() - 1);
    }

    public int victoireJ1(){
        //retourne 1 si le joueur1 a gagné le jeu
        //0 sinon
        scoreJ1++;
        if (jeuFini())
            return 1;
        return 0;
    }

    public int victoireJ2(){
        //retourne 2 si le joueur2 a gagné le jeu
        //0 sinon
        scoreJ2++;
        if (jeuFini())
            return 1;
        return 0;
    }

}
