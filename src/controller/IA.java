package controller;

import static java.lang.Math.random;

public class IA {
    int difficulte;

    public IA(int difficulte) {
        this.difficulte = difficulte;
    }
    public int choisirCoup(Partie partie){

        return (int)random()*partie.getNombreLigne();
    }
    private int evaluerCoup(Partie partie){
        int j,i,x,y,evaluation=0,equipeAdjacente,scoreCaseJ1,scoreCaseJ2;
        for (x=0;x<partie.getNombreLigne();x++){
            for (y=0;y<partie.getNombreColone();y++){
                if(partie.getMatrice()[x][y]==0){
                    if(x>0 && partie.getMatrice()[x-1][y]!=0){
                        equipeAdjacente=partie.getMatrice()[x-1][y];
                        i=2;
                        while(i<partie.getAlignerGagnant()-1 && x-i>0 && partie.getMatrice()[x-i][y]==equipeAdjacente){
                            i=i+1;
                        }
                        if(i==partie.getAlignerGagnant()-1){
                            if(equipeAdjacente==1){
                                scoreCaseJ1=partie.getAlignerGagnant()-1;
                            }else{
                                scoreCaseJ2=partie.getAlignerGagnant()-1;
                            }
                        }
                    }
                    if(y+1<partie.getNombreColone() && partie.getMatrice()[x][y+1]!=0){
                        equipeAdjacente=partie.getMatrice()[x][y+1];
                    }
                    if(y>0 && partie.getMatrice()[x][y-1]!=0){
                        equipeAdjacente=partie.getMatrice()[x][y-1];
                    }
                    if(x>0 && partie.getMatrice()[x-1][y-1]!=0){
                        equipeAdjacente=partie.getMatrice()[x-1][y+1];
                    }
                    if(x+1<partie.getNombreLigne() && y>0 && partie.getMatrice()[x+1][y-1]!=0){
                        equipeAdjacente=partie.getMatrice()[x+1][y-1];
                    }
                    if(x>0 && y+1<partie.getNombreColone() && partie.getMatrice()[x-1][y+1]!=0){
                        equipeAdjacente=partie.getMatrice()[x-1][y+1];
                    }
                    if(x+1<partie.getNombreLigne() && y+1<partie.getNombreColone() && partie.getMatrice()[x+1][y+1]!=0){
                        equipeAdjacente=partie.getMatrice()[x+1][y+1];
                    }

                }
            }
        }
        return evaluation;
    }
}
