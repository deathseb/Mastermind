package fr.rasen.mastermind.JeuPm;

import java.util.Scanner;

public class GameMaster {

	private Entite e;
	private String combinaison = "";


	public GameMaster(Entite e, int nbChiffre) {
		this.e = e;
		creerCombi(nbChiffre);
	}

	public String evalProp(String str) { //Prend la proposition du joueur et retourne la suite de +-= correspondant 
		String reponse = "";
		return reponse;

	}

	public void creerCombi(int nbChiffre) {
		if (e.toString().equals("ordinateur")) {
			for (int i = 0 ; i < nbChiffre; i++) { //génère une combinaison de X chiffres compris entre 0 et 9
				int c = (int) (Math.random() * 9);
				combinaison = combinaison + String.valueOf(c);
			}
		} else {
			System.out.println("Veuillez entrer une combinaison à " + nbChiffre ); //Combinaison donné par l'utilisateur Humain
			Scanner sc = new Scanner(System.in);
			combinaison = sc.nextLine();
		}
	}

	public Entite getE() {
		return e;
	}

	public void setE(Entite e) {
		this.e = e;
	}

	public String getCombinaison() {
		return combinaison;
	}

	public void setCombinaison(String combinaison) {
		this.combinaison = combinaison;
	}


}
