package fr.rasen.mastermind.Mastermind;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Joueur {

    private Entite e;
    private List<Pastille> derProp = null;
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
            derProp = new LinkedList<Pastille>();
            for(int i=0; i < nbChiffre; i++){
                Bille b = new Bille(String.valueOf(prop.charAt(i)));
                derProp.add(b.getPastille());
            }
        } else{
            if(!allColor){
                if (derProp == null) { // créer la première proposition
                    derProp = new LinkedList<Pastille>();
                    for(int i =0; i < nbChiffre; i++){
                        derProp.add(Pastille.BLEU);
                        prop = prop + derProp.get(i).getValeur();
                    }
                } else {
                    List<Pastille> listProp = new LinkedList<Pastille>();
                    for (int i = 0; i < nbChiffre; i++) { // si l'on a déjà un B ou un N alors on garde la même couleur, sinon on passe à la suivante
                        if (derRep.charAt(i) == 'B' || derRep.charAt(i) == 'N') {
                            prop = prop + derProp.get(i).getValeur();
                            listProp.add(new Bille(derProp.get(i).getValeur()).getPastille());
                        } else {
                            prop = prop + derProp.get(i).nextPastille(derProp.get(i).getValeur());
                            Bille b = new Bille(derProp.get(i).getValeur());
                            listProp.add(b.getPastille().nextPastille(b.getPastille().getValeur()));
                        }
                    }
                }
            } else {
                List<Pastille> listProp = new LinkedList<Pastille>();
                for(int i=0; i < nbChiffre; i++){
                    if(derRep.charAt(i) == 'N'){
                        listProp.add(derProp.get(i));
                    }
                }
            }
        }

        return prop;
    }
}
