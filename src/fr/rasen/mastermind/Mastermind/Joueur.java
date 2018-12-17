package fr.rasen.mastermind.Mastermind;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Joueur {

    private Entite e;
    private List<Pastille> derProp = new LinkedList<Pastille>();
    private String derRep;
    private boolean allColor = false;
    private int nbChiffre;
    private Scanner sc = new Scanner(System.in);

    public Joueur (Entite e, int nbChiffre){
        this.e = e;
        this.nbChiffre = nbChiffre;
    }

    public String envoieProp(){
        String prop = "";
        if(e.equals(Entite.HUMAIN)){
            System.out.println("Veuillez entrer votre suite de " + nbChiffre + " chiffres compris en 0 et 9.");
            prop = sc.nextLine();
        } else{
            List<Pastille> listProp = new LinkedList<Pastille>();
            if(!allColor){
                for(int i=0; i < nbChiffre; i++){
                    if(derRep.charAt(i)=='B'){
                        prop = prop + derProp.get(i).getValeur();
                        listProp.add(new Pastille(derProp.get(i).getValeur()));
                    } else{
                        prop = prop + derProp.get(i).nextPastille(derProp.get(i).getValeur());
                        listProp.add(new Pastille(derProp.get(i).nextPastille(derProp.get(i).getValeur())));
                    }
                }
            }
        }
        derProp = prop;
        return prop;
    }
}
