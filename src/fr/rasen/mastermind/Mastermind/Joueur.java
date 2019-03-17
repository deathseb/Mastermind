package fr.rasen.mastermind.Mastermind;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Joueur {

    private Entite e;
    private List<Pastille> derProp = null;
    private List<List<Pastille>> listAncienTours = new LinkedList<List<Pastille>>(); //Ancien tours joué
    private List<String> listAncienneRep = new LinkedList<String>(); // anciennes réponses
    private List<Pastille> listCouleurCombi = null;
    private boolean premierTour = true;
    private int numBouleModif = 0;
    private int numCouleurList = 0;
    private String derRep;
    private boolean allColor = false;
    private int nbChiffre;
    private Scanner sc = new Scanner(System.in);

    public Joueur(Entite e, int nbChiffre) {
        this.e = e;
        this.nbChiffre = nbChiffre;
    }

    /**
     * Créer la proposition de solution.
     * @return la proposition sous forme de String.
     */
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
            listAncienTours.add(derProp);
        } else { // PARTIE ALGORITHME
            if (derProp == null) { // créer la première proposition
                derProp = new LinkedList<Pastille>();
                for (int i = 0; i < nbChiffre; i++) {
                    derProp.add(Pastille.BLEU);
                    prop = prop + derProp.get(i).getValeur();
                }
                listAncienTours.add(derProp);
                return prop;
            }
            if (!allColor) { // Si l'ordi n'a pas trouvé toutes les couleurs de la combinaison
                List<Pastille> listProp = new LinkedList<Pastille>();
                for(int i = 0; i < nbChiffre;  i++){
                    if(i < getNbNoirEtBlanc()) { // On ajoute les valeurs ayant obtenue une boule blanche ou noir
                        prop = prop + derProp.get(i).getValeur();
                        listProp.add(derProp.get(i));
                    } else { // on passe au la couleur suivante pour les restantes
                        Bille b = new Bille(derProp.get(i));
                        listProp.add(b.nextPastille());
                        prop = prop + listProp.get(i).getValeur();
                    }
                }
                listAncienneRep.add(derRep);
                listAncienTours.add(listProp);
                derProp = listProp;
                return prop;
            } else { //Si l'ordi a trouvé toutes les couleurs
                if(listCouleurCombi==null){ //Créé la liste des couleurs formant la combinaison
                    listCouleurCombi = new LinkedList<>();
                    for(int i = 0; i<derProp.size(); i++){
                        listCouleurCombi.add(derProp.get(i));
                    }
                }
                prop = trouverCombi();
            }
        }
        return prop;
    }

    /**
     * Recherche la combinaison de Pastille.
     * @return la proposition sous forme de String.
     */
    public String trouverCombi(){
        List<Pastille> list = new ArrayList<>();
        String str = "";
        if (premierTour){
            premierTour = false;
            list.add(listCouleurCombi.get(numCouleurList+1));
            for(int i=1; i<derProp.size(); i++){
                list.add(derProp.get(i));
            }
            numCouleurList = 1;
            derProp = list;
            listAncienneRep.add(derRep);
            for(int i =0; i<list.size(); i++){
                str += list.get(i).getValeur();
            }
            return str;
        } else{
            String rep1 = listAncienneRep.get(listAncienneRep.size()-1);
            if(getNbNoir(derRep) > getNbNoir(rep1)){ //Si le changement de couleur à créé une nouvelle boule noire
                numBouleModif ++;
                numCouleurList = 0;
                for (int i =0; i<derProp.size(); i++){
                    if(i == numBouleModif){
                        list.add(listCouleurCombi.get(numCouleurList));
                    }else{
                        list.add(derProp.get(i));
                    }
                }
                derProp = list;
                listAncienneRep.add(derRep);
                for (int j =0; j<list.size(); j++){
                    str += list.get(j).getValeur();
                }
                return str;


            } else if (getNbNoir(derRep) == getNbNoir(rep1)){ //Si le changement de couleur n'a rien fait
                numCouleurList ++;
                for (int i =0; i<derProp.size(); i++) {
                    if (i == numBouleModif) {
                        list.add(listCouleurCombi.get(numCouleurList));
                    } else {
                        list.add(derProp.get(i));
                    }
                }
                derProp = list;
                listAncienneRep.add(derRep);
                for (int j = 0; j < list.size(); j++) {
                    str += list.get(j).getValeur();
                }
                return str;


            } else{ //Si le changement de couleur à supprimé une boule noire
                numCouleurList --;
                for (int i =0; i<derProp.size(); i++) {
                    if (i == numBouleModif) {
                        list.add(listCouleurCombi.get(numCouleurList));
                    } else if( i == numBouleModif+1){
                        list.add(listCouleurCombi.get(0));
                    } else {
                        list.add(derProp.get(i));
                    }
                }
                numBouleModif ++;
                numCouleurList =0;
                derProp = list;
                listAncienneRep.add(derRep);
                for (int j = 0; j < list.size(); j++) {
                    str += list.get(j).getValeur();
                }
                return str;
            }
        }
    }

    public int getNbNoir(String rep){
        int nb =0;
        for (int i =0; i < rep.length(); i++){
            if(rep.charAt(i)=='N'){
                nb++;
            }
        }
        return nb;
    }

    public List<Pastille> getDerProp() {
        return derProp;
    }

    public void setAllColor(String rep){
        boolean ok =true;
        for(int i =0; i < nbChiffre; i++){
            if(rep.charAt(i) != 'N' && rep.charAt(i) != 'B'){
                ok = false;
            }
        }
        allColor = ok;
    }

    public boolean isAllColor() {
        return allColor;
    }

    public void setDerRep(String str){
        derRep = str;
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
