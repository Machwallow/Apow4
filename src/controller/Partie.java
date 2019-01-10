package controller;

import java.util.ArrayList;

public class Partie {
    private int nombreColone,nombreLigne,alignerGagnant;
    private ArrayList<Integer> listeCoup;
    private int [][] Matrice;

    public int[][] getMatrice() {
        return Matrice;
    }

    public int getNombreColone() {
        return nombreColone;
    }

    public int getNombreLigne() {
        return nombreLigne;
    }

    public Partie(int nombreColone, int nombreLigne, int alignerGagnant) {
        this.nombreColone = nombreColone;
        this.nombreLigne = nombreLigne;
        this.alignerGagnant = alignerGagnant;
        Matrice=new int[nombreLigne][nombreColone];
        listeCoup=new ArrayList<Integer>();
    }

    public int getAlignerGagnant() {
        return alignerGagnant;
    }

    public int ajouterJeton(int joueur, int y){
        //retourne -1 en cas d'erreur
        //retourne 1 en cas de victoire
        //retourne 2 en cas de match nul
        //retourne 0 en cas de succes

        int x=0;
        int i,k;
        int nbAligne;
        boolean gagner;

        while (x<nombreLigne-1 && Matrice[x][y]!=0){
            x=x+1;
        }
        if(x==nombreLigne-1 && Matrice[x][y]!=0)
            return -1;
        listeCoup.add(y);
        Matrice[x][y]=joueur;
        if(x>alignerGagnant-1) {
            i = (x - (alignerGagnant-1));
            gagner=true;
            while (i < x && gagner){
                //System.out.println(i+"   "+x+"\n");
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
        i=nombreColone-y;
        if(x<nombreColone-y)
            i=-x;
        //System.out.println(" lalal ");
        while (i+(nombreColone-y)<nombreColone && i+x<nombreLigne && nbAligne<alignerGagnant){
            //System.out.println(x+"  "+y+"  "+(i-y)+"  "+(i+(nombreColone-y))+"  "+ (i+x));
            if(Matrice [i+x][i+(nombreColone-y)]==joueur){
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
                if(Matrice [nombreLigne-1][i]==0)
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
        //System.out.println(i+"  "+listeCoup.get(listeCoup.size()-1));
        while(Matrice[i][listeCoup.get(listeCoup.size()-1)]==0){
            i=i-1;
        }
        //System.out.println(i+"  "+listeCoup.get(listeCoup.size()-1)+"  "+Matrice[i][listeCoup.get(listeCoup.size()-1)]);
       // System.out.println(this);
        Matrice[i][listeCoup.get(listeCoup.size()-1)]=0;
        listeCoup.remove(listeCoup.size()-1);

    }
    public String toString(){
        int x=0,y=0;
        String ligne="";
        String retour="";
        while(x<nombreLigne){
            y=0;
            ligne="";
            while(y<nombreColone){
                if(Matrice[x][y]==1 || Matrice[x][y]==0)
                    ligne=ligne+Matrice[x][y]+"   ";
                else
                    ligne=ligne+Matrice[x][y]+"  ";
                y=y+1;
            }
            retour+=ligne+"\n";
            x=x+1;

        }
    return retour;
    }
}
