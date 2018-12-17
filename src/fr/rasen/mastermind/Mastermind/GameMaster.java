package fr.rasen.mastermind.Mastermind;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GameMaster {

    private Scanner scanner = new Scanner(System.in);
    private Entite joueur;
    private List<Pastille> combi = new LinkedList<>();
    private int nbChiffre;
    private String [] eval;

    public GameMaster(Entite joueur, int nbChiffre){
        this.joueur = joueur;
        this.nbChiffre = nbChiffre;
        creerCombi();
        eval = new String[nbChiffre];
    }

    public void creerCombi(){
        if(joueur.equals(Entite.ORDI)){
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
        } else {
            System.out.println("Entrez une combinaison de " + nbChiffre + " chiffres (de 0 à 9) à trouver par l'ordinateur.");
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

    public String evalProp (String str){
        String rep = "";
        if(joueur.equals(Entite.ORDI)){
            for (int k =0; k < nbChiffre; k++){
                eval[k] = "_";
            }
            for(int i = 0; i < nbChiffre; i++){
                if(combi.get(i).getValeur().charAt(0) == (str.charAt(i))){
                    eval[i] = "N"; //cas boule noire
                }
            }
            for(int i=0; i < nbChiffre; i++){
                for(int j = 0; j < nbChiffre; j++){
                    if(combi.get(j).getValeur().charAt(0)== str.charAt(i) && eval[i] == "_"){
                        eval[i] = "B"; //cas boule blanche
                    }
                }
            }
            for (int i =0; i < nbChiffre; i++){
                rep = rep + eval[i];
            }
        } else{
            System.out.println("Veuillez donner la suite de N (couleur bonne et bien placé), B (couleur bonne mais mal placée) ou _ (si ce n'est pas bon) correspondant à la proposition.");
            System.out.println("Si rien n'est bon, appuyez sur entré.");
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
}
