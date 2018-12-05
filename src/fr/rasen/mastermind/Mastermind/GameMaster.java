package fr.rasen.mastermind.Mastermind;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GameMaster {

    private Scanner sc = new Scanner(System.in);
    private Entite e;
    private List<Pastille> combi = new LinkedList<>();
    private int nbChiffre;

    public GameMaster(Entite e, int nbChiffre){
        this.e = e;
        this.nbChiffre = nbChiffre;
        creerCombi();
    }

    public void creerCombi(){
        if(e.equals(Entite.ORDI)){
            for(int i = 0; i < nbChiffre; i++){
                int p = (int) (Math.random()*9);
                switch (p){
                    case 0:
                        combi.add(Pastille.BLEU);
                        break;
                    case 1:
                        combi.add(Pastille.BLEU_CLAIR);
                        break;
                    case 2:
                        combi.add(Pastille.GRIS);
                        break;
                    case 3:
                        combi.add(Pastille.JAUNE);
                        break;
                    case 4:
                        combi.add(Pastille.MARRON);
                        break;
                    case 5:
                        combi.add(Pastille.ORANGE);
                        break;
                    case 6:
                        combi.add(Pastille.ROSE);
                        break;
                    case 7:
                        combi.add(Pastille.ROUGE);
                        break;
                    case 8:
                        combi.add(Pastille.VERT);
                        break;
                    case 9:
                        combi.add(Pastille.VIOLET);
                        break;
                }
            }
        }
    }

    public void evalProp (String str){
        String rep = "";
        if(e.equals(Entite.ORDI)){
            for(int i = 0; i < nbChiffre; i++){
                if(str.charAt(i) == Integer.toString(combi.get(i).getValeur()).charAt(0)){ // évaluation boule noire
                    rep = rep + Pastille.NOIR.getValeur();
                }
            }
        }
    }
}
