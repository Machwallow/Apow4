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
        int j,i,x,y,evaluation=0,equipeAdjacente1=0,equipeAdjacente2,scoreCaseJ1,scoreCaseJ2,sens,score,axe,deltaX,deltaY;
        for (x=0;x<partie.getNombreLigne();x++){
            for (y=0;y<partie.getNombreColone();y++){
                if(partie.getMatrice()[x][y]==0){
                    for(axe=1;axe<=4;axe++){
                        switch (axe) {
                            case 1 :
                                deltaX = 1;
                                deltaY = 0;
                                break;
                            case 2 :
                                deltaX = 0;
                                deltaY = 1;
                                break;
                            case 3 :
                                deltaX = 1;
                                deltaY = 1;
                                break;
                            default :
                                deltaX = 1;
                                deltaY = -1;
                        }
                        sens=1;
                        score=0;
                        for (j=0;j<2;j++){ //on vas dans les deux sens pour chaque axe
                            if(     (deltaX==0 || (x>0 && sens*deltaX ==-1) ||(sens*deltaX==1 && x+1<partie.getNombreLigne())) &&
                                    (deltaY==0 || ((y>0 && sens*deltaY ==-1) ||(sens*deltaY==1 && y+1<partie.getNombreLigne())))
                                && partie.getMatrice()[x+sens*deltaX][y+sens*deltaY]!=0){


                                if(sens==-1){//deuxieme sens testé
                                    equipeAdjacente2=equipeAdjacente1;
                                    equipeAdjacente1=partie.getMatrice()[x+sens*deltaX][y+sens*deltaY];
                                    if(equipeAdjacente2!=equipeAdjacente1){ // si dans les deux sens l'equipe est la même le score se cumule sinon il se reset
                                        score=0;
                                    }
                                }
                                equipeAdjacente1=partie.getMatrice()[x+sens*deltaX][y+sens*deltaY];

                                i=2;
                                while(i<partie.getAlignerGagnant()-1
                                        && ((x+sens*i>0 && sens==-1) || sens==1 && x+1<partie.getNombreLigne())
                                        && partie.getMatrice()[x+(sens*i)][y]==equipeAdjacente1){
                                    score=score+1;
                                    i=i+1;
                                }
                            }
                            sens=-1;
                            if(score>=partie.getAlignerGagnant()-1){
                                if(equipeAdjacente1==1){
                                    scoreCaseJ1=partie.getAlignerGagnant()-1;
                                }else{
                                    scoreCaseJ2=partie.getAlignerGagnant()-1;
                                }
                            }
                        }
                    }
                    if(y+1<partie.getNombreColone() && partie.getMatrice()[x][y+1]!=0){
                        equipeAdjacente1=partie.getMatrice()[x][y+1];
                    }
                    if(y>0 && partie.getMatrice()[x][y-1]!=0){
                        equipeAdjacente1=partie.getMatrice()[x][y-1];
                    }
                    if(x>0 && partie.getMatrice()[x-1][y-1]!=0){
                        equipeAdjacente1=partie.getMatrice()[x-1][y+1];
                    }
                    if(x+1<partie.getNombreLigne() && y>0 && partie.getMatrice()[x+1][y-1]!=0){
                        equipeAdjacente1=partie.getMatrice()[x+1][y-1];
                    }
                    if(x>0 && y+1<partie.getNombreColone() && partie.getMatrice()[x-1][y+1]!=0){
                        equipeAdjacente1=partie.getMatrice()[x-1][y+1];
                    }
                    if(x+1<partie.getNombreLigne() && y+1<partie.getNombreColone() && partie.getMatrice()[x+1][y+1]!=0){
                        equipeAdjacente1=partie.getMatrice()[x+1][y+1];
                    }

                }
            }
        }
        return evaluation;
    }
}
