package fr.rasen.mastermind.Mastermind;

import java.util.Scanner;

public class Joueur {

    private Entite e;
    private String derProp;
    private String derRep;
    private int nbChiffre;
    private Scanner sc = new Scanner(System.in);

    public Joueur (Entite e, int nbChiffre){
        this.e = e;
        this.nbChiffre = nbChiffre;
    }

    public String envoieProp(){
        String prop = "";
        if(e.equals(Entite.HUMAIN)){
            System.out.println("Veuillez entrer votre proposition.");
            prop = sc.nextLine();
        }
        derProp = prop;
        return prop;
    }
}
