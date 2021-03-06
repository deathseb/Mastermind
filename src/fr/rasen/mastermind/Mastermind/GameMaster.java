package fr.rasen.mastermind.Mastermind;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GameMaster {

    private Scanner scanner = new Scanner(System.in);
    private Entite joueur;
    private List<Pastille> combi = new LinkedList<>();
    private int nbChiffre;
    private int nbCouleursMax;
    private String [] eval;

    public GameMaster(Entite joueur, int nbChiffre, int couleurs){
        this.joueur = joueur;
        this.nbChiffre = nbChiffre;
        nbCouleursMax = couleurs;
        if(joueur.equals(Entite.ORDI)) {
            creerCombi();
        }
        eval = new String[nbChiffre];
    }

    /**
     * Ajoute la ocmbinaison rentré par le joueur en mode défenseur.
     * @param list
     */
    public void creerCombi(List<Pastille> list){
        combi = list;
    }

    /**
     * Créé une combinaison de façon aléatoire pour le mode challenger.
     */
    public void creerCombi(){
        if(joueur.equals(Entite.ORDI)){
            for(int i = 0; i < nbChiffre; i++){
                int p = (int) (Math.random()*nbCouleursMax);
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
        } else {
            System.out.println("Entrez une combinaison de " + nbChiffre + " chiffres (de 0 à " + (nbCouleursMax-1) + ") à trouver par l'ordinateur.");
            String str = scanner.nextLine();
            for(int i = 0; i < str.length(); i++){
                switch (str.charAt(i)){
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

    /**
     * Compare la proposition faite avec la combinaison.
     * @param str proposition faite par le joueur.
     * @return suite de B, N ou _ permettant au joueur de calculer son prochain coup.
     */
    public String evalProp (String str){
        String rep = "";
        eval = new String[nbChiffre];
        if(joueur.equals(Entite.ORDI)) {
            for (int k =0; k < nbChiffre; k++){
                eval[k] = "_";
            }

            for(int i = 0; i < nbChiffre; i++){
                if(combi.get(i).getValeur().charAt(0) == (str.charAt(i))){
                    eval[i] = "N"; //cas boule noire
                }
            }

            for(int i=0; i < nbChiffre; i++){ // cas Boule Blanche
               if(eval[i] == "_"){
                   for(int j =i; j < nbChiffre; j++){
                       if(str.charAt(i) == combi.get(j).getValeur().charAt(0) && eval[j] == "_"){ //si la couleur de la proposition est présente dans la solution
                           eval[i]="B";
                       }
                   }
               }
            }

            for (int i =0; i < nbChiffre; i++){ //utilise les trois boucles for pour donner une réponse désordonnée
                if(eval[i]=="_"){
                    rep = rep + eval[i];
                }
            }
            for(int i =0; i < nbChiffre; i++){
                if(eval[i] == "B"){
                    rep = rep + eval[i];
                }
            }
            for(int i = 0; i < nbChiffre; i++){
                if(eval[i]=="N"){
                    rep = rep + eval[i];
                }
            }
        } else{
            System.out.println("Veuillez donner la suite de N (couleur bonne et bien placé), B (couleur bonne mais mal placée) ou _ (si ce n'est pas bon) correspondant à la proposition.");
            rep = scanner.nextLine();
        }
        return rep;
    }

    public String showCombi(){
        String rep = "";
        for (int i=0; i<nbChiffre; i++){
            rep = rep + combi.get(i).getValeur();
        }
        return rep;
    }

    public List<Pastille> getCombi() {
        return combi;
    }
}
