package fr.rasen.mastermind.JeuPm;

import java.util.Scanner;

public class GameMaster {

	private Entite e;
	private String combinaison = "";
	private Scanner sc = new Scanner(System.in); 


	/**
	 * création du GameMaster, celui qui connait la combinaison secrète
	 * @param e Qui connait la combinaison, l'humain ou l'ordinateur
	 * @param nbChiffre nombre de chiffre composant la combinaison
	 */
	public GameMaster(Entite e, int nbChiffre) {
		this.e = e;
		creerCombi(nbChiffre);
	}

	/**
	 * Méthode retournant un string de + - ou = servant d'indication au joueur 
	 * @param str chaine de caractère envoyé par le joueur
	 * @return chaine de caractère destiné à permettre au joueur de se rapprocher de la combinaison
	 */
	public String evalProp(String str) { //str doit bien être une série de chiffre et uniquement de chiffre 
		String reponse = "";
		if (e.toString().equals("ordinateur")) { //évaluation de la proposition par l'ordinateur
			for (int i = 0; i < combinaison.length(); i++) {
				int j = str.charAt(i);
				int k = combinaison.charAt(i);
				if(j == k)
					reponse = reponse + "=";
				else if (j < k)
					reponse = reponse + "+";
				else
					reponse = reponse + "-";
			}
		} else { //évaluation de la proposition par l'humain
			System.out.println("Veuillez entrer la série de + - et = correspondant à la proposition soumise");
			reponse = sc.nextLine(); // gestion d'erreur à faire
		}
		return reponse;

	}

	/**
	 * Méthode créant la combinaison aléatoirement si l'ordinateur est GM ou la demandant à l'humain si il est le GM
	 * @param nbChiffre nombre de chiffre composant la combinaison mystère
	 */
	public void creerCombi(int nbChiffre) {
		if (e.toString().equals("ordinateur")) {
			for (int i = 0 ; i < nbChiffre; i++) { //génère une combinaison de X chiffres compris entre 0 et 9
				int c = (int) (Math.random() * 9);
				combinaison = combinaison + String.valueOf(c);
			}
		} else {
			System.out.println("Veuillez entrer une combinaison à " + nbChiffre ); //Combinaison donné par l'utilisateur Humain
			combinaison = sc.nextLine(); //Gestion d'erreur à faire
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
