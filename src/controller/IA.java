package controller;

import static java.lang.Math.max;
import static java.lang.Math.random;

public class IA {
    int difficulte;

    public IA(int difficulte) {
        this.difficulte = difficulte;
    }

    private int evaluerCoup(Partie partie,int prochainJoueur){
        int j,i,x,y,evaluation=0,equipeAdjacente1=0,equipeAdjacente2,scoreCaseJ1,scoreCaseJ2,sens,score,axe,deltaX,deltaY,potentiel;
        int nombreGagnantJ1=0,nombreGagnantJ2=0;
        boolean interonpue;
        for (x=0;x<partie.getNombreLigne();x++){
            for (y=0;y<partie.getNombreColone();y++){
                scoreCaseJ1=0;
                scoreCaseJ2=0;
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
                        score=1;
                        potentiel=1;
                        for (j=0;j<2;j++){ //on vas dans les deux sens pour chaque axe
                            if(     (deltaX==0 || (x>0 && sens*deltaX ==-1) ||(sens*deltaX==1 && x+1<partie.getNombreLigne())) &&
                                    (deltaY==0 || ((y>0 && sens*deltaY ==-1) ||(sens*deltaY==1 && y+1<partie.getNombreColone())))
                                ){//&& partie.getMatrice()[x+sens*deltaX][y+sens*deltaY]!=0


                                if(sens==-1){//deuxieme sens testé
                                    equipeAdjacente2=equipeAdjacente1;
                                    equipeAdjacente1=partie.getMatrice()[x+sens*deltaX][y+sens*deltaY];
                                    if(equipeAdjacente2!=equipeAdjacente1){ // si dans les deux sens l'equipe est la même le score se cumule sinon il se reset
                                        score=1;
                                        potentiel=1;
                                    }
                                }
                                equipeAdjacente1=partie.getMatrice()[x+sens*deltaX][y+sens*deltaY];

                                i=2;
                                interonpue=false;
                                while(i<partie.getAlignerGagnant()-1
                                        && (deltaX==0 ||((x+sens*deltaX*i>0 && sens*deltaX==-1) ||
                                                (sens*deltaX==1 && x+sens*deltaX*i<partie.getNombreLigne())))
                                        && (deltaY==0 ||((y+sens*deltaY*i>0 && sens*deltaY==-1)||
                                                (sens*deltaY==1 && y+sens*deltaY*i<partie.getNombreColone())))
                                        && (partie.getMatrice()[x+sens*deltaX][y+sens*deltaY]==equipeAdjacente1 ||
                                        partie.getMatrice()[x+sens*deltaX][y+sens*deltaY]==0)){
                                    if(partie.getMatrice()[x+sens*deltaX][y+sens*deltaY]==0)
                                        interonpue=true;
                                    if(!interonpue)
                                        score=score+1;
                                    potentiel=potentiel+1;
                                    i=i+1;
                                }
                                if(potentiel>=partie.getAlignerGagnant()-1 && score>1){
                                    if(equipeAdjacente1==1 &&  scoreCaseJ1<score){
                                        scoreCaseJ1=3*score+potentiel;
                                    }
                                    if(equipeAdjacente1!=1 &&  scoreCaseJ2<score){
                                        scoreCaseJ2=3*score+potentiel;
                                    }
                                }
                            }
                            sens=-1;
                        }

                    }
                    if((x==0 || partie.getMatrice()[x][y]!=0) && scoreCaseJ1==partie.getAlignerGagnant()-1){
                        nombreGagnantJ1+=1;
                    }
                    if((x==0 || partie.getMatrice()[x][y]!=0) && scoreCaseJ2==partie.getAlignerGagnant()-1){
                        nombreGagnantJ2+=1;
                    }

                    evaluation+=(scoreCaseJ1*scoreCaseJ1)-(scoreCaseJ2*scoreCaseJ2);
                }
            }
        }
        if(prochainJoueur==1){
            if(nombreGagnantJ1>=1)
                evaluation=9000; // 900 c'est beaucoup // c'est 9000 là c'est chaud
            else{
                if(nombreGagnantJ2>=2)
                    evaluation=-9000;
            }
        }else{
            if(nombreGagnantJ2>=1)
                evaluation=-9000;
            else{
                if(nombreGagnantJ1>=2)
                    evaluation=9000;
            }
        }
        // négatif pour joueur 2 et positif pour joueur 1
        // joueur 1 c'est celui dont le jeton vaut 1
        // joueur 1 c'est celui dont le jeton ne vaut pas 1
        return evaluation;
    }

    public int choisirCoup(Partie partie,int joueur){
        int y, score,maxscore=-9999999*joueur,yRetour=-1,ajoutJeton;
        for (y = 0; y < partie.getNombreColone(); y++) {
            ajoutJeton=partie.ajouterJeton(joueur, y);
            if(ajoutJeton!=-1) {//si l'ajout a bien eu lieu
                if(ajoutJeton!=0){//victoire ou match nul
                  //  System.out.println("Choisir Coup 1 "+joueur);
                    partie.annuler();
                    return y;
                }
                score = ComparerCoup(partie, 1, joueur * -1);
                //System.out.println(score+"  "+maxscore+"  "+joueur);
               // System.out.println(score+"  "+maxscore+"  "+joueur+"  "+y+"  li");
                if ((joueur == 1 && score >= maxscore) || (joueur != 1 && score <= maxscore)) {
                  //  System.out.println(score+"  "+maxscore+"  "+joueur+"  "+y+"  lala");
                    if (score == maxscore) {
                        if (Math.random() < 0.4) {
                            yRetour = y;
                        }
                    } else {
                        maxscore = score;
                        yRetour = y;
                    }

                }
              //  System.out.println("Choisir Coup2 "+y+"  "+joueur);
                partie.annuler();
            }
        }
        System.out.println(yRetour+"  "+maxscore+"  "+joueur);
        return yRetour;
    }
    private int ComparerCoup(Partie partie, int i,int joueur){
        int y,score,maxscore=-9999999*joueur,ajoutJeton;
        if(i>=difficulte){
            return evaluerCoup(partie,joueur);
        }
        for (y = 0; y < partie.getNombreColone(); y++) {
            ajoutJeton=partie.ajouterJeton(joueur, y);
          //  System.out.println("ComparerCoup1 "+y+"  "+joueur+"  "+ajoutJeton);
            if(ajoutJeton!=-1) {//si l'ajout a bien eu lieu
                if(ajoutJeton==1){
                    partie.annuler();
                    return (1000000*joueur);
                }
                if(ajoutJeton==2){
                    partie.annuler();
                    return 0;
                }

                score = ComparerCoup(partie, i + 1, joueur * -1);
                if ((joueur == 1 && score > maxscore) || (joueur != 1 && score < maxscore)) {
                    maxscore = score;
                }
              //  System.out.println("ComparerCoup2  "+y+"  "+joueur+"  "+ajoutJeton);
                partie.annuler();
            }
        }
        return (int) (0.9*maxscore); // *0.9 pour faire durée la partie même quand on sais qu'on va perdre
        // et pour encourager a gagné en le moins de tours possible
    }
}
