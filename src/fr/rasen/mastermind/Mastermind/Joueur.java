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

    public Joueur(Entite e, int nbChiffre) {
        this.e = e;
        this.nbChiffre = nbChiffre;
    }

    public String envoieProp() {
        String prop = "";
        if (e.equals(Entite.HUMAIN)) {
            System.out.println("Veuillez entrer votre suite de " + nbChiffre + " chiffres compris en 0 et 9.");
            prop = sc.nextLine();
            derProp = new LinkedList<Pastille>();
            for (int i = 0; i < nbChiffre; i++) {
                Bille b = new Bille(String.valueOf(prop.charAt(i)));
                derProp.add(b.getPastille());
            }
        } else {
            if (derProp == null) { // créer la première proposition
                derProp = new LinkedList<Pastille>();
                for (int i = 0; i < nbChiffre; i++) {
                    derProp.add(Pastille.BLEU);
                    prop = prop + derProp.get(i).getValeur();
                }
                return prop;
            }
            if (!allColor) { // Si l'ordi n'a pas trouvé toutes les couleurs de la combinaison
                List<Pastille> listProp = new LinkedList<Pastille>();
                for(int i = 0; i < nbChiffre;  i++){
                    if(i < getNbNoirEtBlanc()) { // On ajoute les valeurs ayant obtenue une boule blanche ou noir
                        prop = derProp.get(i).getValeur();
                        listProp.add(derProp.get(i));
                    } else { // on passe au la couleur suivante pour les restantes
                        listProp.add(derProp.get(i).nextPastille(derProp.get(i).getValeur()));
                        prop = listProp.get(i).getValeur();
                    }
                }
            } else {
                List<Pastille> listProp = new LinkedList<Pastille>();
                for (int i = 0; i < nbChiffre; i++) {
                    if (derRep.charAt(i) == 'N') {
                        listProp.add(derProp.get(i));
                    }
                }
            }
        }


        return prop;
    }

    public int getNbNoirEtBlanc() {
        int nombre = 0;
        for (int i = 0; i < nbChiffre; i++) {
            if (derRep.charAt(i) == 'N' || derRep.charAt(i) == 'B') {
                nombre = nombre + 1;
            }
        }
        return nombre;
    }
}
