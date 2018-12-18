package controller;

import java.util.ArrayList;

public class Partie {
    private int nombreColone,nombreLigne,alignerGagnant,tempsTour;
    private ArrayList<Integer> listeCoup;
    private int [][] Matrice;

    public Partie(int nombreColone, int nombreLigne, int alignerGagnant, int tempsTour) {
        this.nombreColone = nombreColone;
        this.nombreLigne = nombreLigne;
        this.alignerGagnant = alignerGagnant;
        this.tempsTour = tempsTour;
        Matrice=new int[nombreLigne][nombreColone];
    }
    public int ajouterJeton(int joueur, int y){
        int x=0;
        int i,k;
        int nbAligne;
        boolean gagner;

        while (x<nombreLigne && Matrice[x][y]!=0){
            x=x+1;
        }
        if(x==nombreLigne)
            return -1;
        listeCoup.add(y);
        Matrice[x][y]=joueur;
        if(x>=alignerGagnant-1) {
            i = (x - alignerGagnant-1);
            gagner=true;
            while (i < x && gagner){
                if(Matrice[i][y]!=joueur)
                    gagner=false;
                i=i+1;
            }
            if(gagner)
                return 1;
        }
        i=0;
        nbAligne=0;
        while (i<nombreColone && nbAligne<alignerGagnant){
            if(Matrice [x][i]==joueur){
                nbAligne=nbAligne+1;
            }else{
                nbAligne=0;
            }
            i=i+1;
        }
        if(nbAligne==alignerGagnant)
            return 1;


        i=-y;
        if(x<y)
            i=-x;
        nbAligne=0;
        while (i+y<nombreColone && i+x<nombreLigne && nbAligne<alignerGagnant){
            if(Matrice [i+x][i+y]==joueur){
                nbAligne=nbAligne+1;
            }else{
                nbAligne=0;
            }
            i=i+1;
        }
        if(nbAligne==alignerGagnant)
            return 1;

        nbAligne=0;
        i=-y;
        if(x<y)
            i=-x;
        while (i-y<nombreColone && i+x<nombreLigne && nbAligne<alignerGagnant){
            if(Matrice [i+x][i+y]==joueur){
                nbAligne=nbAligne+1;
            }else{
                nbAligne=0;
            }
            i=i+1;
        }
        if(nbAligne==alignerGagnant)
            return 1;
        if(x==nombreLigne-1){
            i=0;
            boolean plein=true;
            while (i<nombreColone && plein){
                if(Matrice [nombreLigne][i]==0)
                    plein=false;
                i=i+1;
            }
            if(plein)
                return 2;
        }
        return 0;
    }

    public void annuler(){
        if(listeCoup.size()==0)
            return;
        int i =nombreLigne-1;
        while(Matrice[i][listeCoup.get(listeCoup.size()-1)]==0){
            i=i+1;
        }
        Matrice[i][listeCoup.get(listeCoup.size()-1)]=0;
        listeCoup.remove(listeCoup.size()-1);

    }
}
