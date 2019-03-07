package fr.rasen.mastermind.JeuPm;

import java.util.Scanner;

public class GameMaster {

	private Entite e;
	private String combinaison = "";
	private Scanner sc = new Scanner(System.in); 

	public GameMaster(Entite e, int nbChiffre) {
		this.e = e;
		creerCombi(nbChiffre);
	}

	/**
	 * Compare la proposition du joueur avec la combinaison.
	 * @param str proposition du joueur.
	 * @return indication pour le joueur.
	 */
	public String evalProp(String str) { //str doit bien être une série de chiffre et uniquement de chiffre 
		String reponse = "";
		//if (e.toString().equals("ordinateur")) { //évaluation de la proposition par l'ordinateur
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
		/*}*/ /*else { //&eacute;valuation de la proposition par l'humain
			System.out.println("Veuillez entrer la s&eacute;rie de + - et = correspondant &agrave; la proposition soumise");
			reponse = sc.nextLine(); // gestion d'erreur &agrave; faire
		}*/
		return reponse;

	}

	/**
	 * Créé une combinaison de façon aléatoire.
	 * @param nbChiffre nombre de chiffre que doit posséder la combinaison.
	 */
	public void creerCombi(int nbChiffre) {
		if (e.toString().equals("ordinateur")) {
			for (int i = 0 ; i < nbChiffre; i++) { //génère une combinaison de X chiffres compris entre 0 et 9
				int c = (int) (Math.random() * 9);
				combinaison = combinaison + (c);
			}
		} else {
			/*System.out.println("Veuillez entrer une combinaison à " + nbChiffre +" chiffres" ); //Combinaison donné par l'utilisateur Humain
			combinaison = sc.nextLine(); //Gestion d'erreur à faire*/
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
