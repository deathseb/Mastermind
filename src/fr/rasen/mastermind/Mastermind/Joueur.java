package fr.rasen.mastermind.Mastermind;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Joueur {

    private Entite e;
    private List<Pastille> derProp = null;
    private List<List<Pastille>> listAncienTours = new LinkedList<List<Pastille>>(); //Ancien tours joué
    private List<String> listAncienneRep = new LinkedList<String>(); // anciennes réponses
    private int posChiffreADeplacer = -1;
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
                if(getNbNoir(derRep)<getNbNoir(listAncienneRep.get(listAncienneRep.size()-1))){
                    for(int i=0; i < nbChiffre; i++){
                        prop = prop +listAncienTours.get(listAncienTours.size()-2).get(i).getValeur(); // Si l'on a moins de noire qu'au tour d'avant, on revient à l'ancienne forme.
                    }
                    if(posChiffreADeplacer >= nbChiffre-2){
                        posChiffreADeplacer = -1;
                    } else{
                        posChiffreADeplacer++;
                    }
                } else{
                    if(getNbNoir(derRep) >= getNbNoir(listAncienneRep.get(listAncienneRep.size()-1))){ // Si on a une nouvelle boule noire, on augmente la position de la boule à bouger, et si elle est au max on recommence du début.
                        if(posChiffreADeplacer >= nbChiffre-2){
                            posChiffreADeplacer = -1;
                        } else{
                            posChiffreADeplacer++;
                        }
                    }
                    Pastille a, b;
                    List<Pastille> p = new LinkedList<Pastille>(); // On intervertit 2 boules une fois par tour.
                    a = derProp.get(posChiffreADeplacer+1);
                    b = derProp.get(posChiffreADeplacer);
                    for(int i = 0; i < nbChiffre; i++){
                        if(i == posChiffreADeplacer){
                            prop = prop + a.getValeur();
                            p.add(a);
                        }else {
                            if (i == posChiffreADeplacer + 1) {
                                prop = prop + b.getValeur();
                                p.add(b);
                            } else {
                                p.add(derProp.get(i));
                                prop = prop + derProp.get(i).getValeur();
                            }
                        }
                    }
                    listAncienneRep.add(derRep);
                    listAncienTours.add(p);
                    derProp = p;
                    return prop;
                }

            }
        }


        return prop;
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

    public void setAllColor(String rep){
        boolean ok =true;
        for(int i =0; i < nbChiffre; i++){
            if(rep.charAt(i) != 'N' && rep.charAt(i) != 'B'){
                ok = false;
            }
        }
        allColor = ok;
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
