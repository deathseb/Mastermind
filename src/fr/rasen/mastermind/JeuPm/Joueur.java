package fr.rasen.mastermind.JeuPm;

import java.util.Scanner;

public class Joueur {

	private Entite e;
	private String derProp;
	private String derRep;
	private int nbChiffre;
	private Scanner sc = new Scanner(System.in);


	/**
	 * création du joueur, càd celui qui fera les proposition et cherchera la combinaison mystère
	 * @param e
	 */
	public Joueur(Entite e, int nbChiffre) {
		this.e = e;
		this.nbChiffre = nbChiffre;
	}


	/**
	 * Méthode permettant de récupérer la chaine de caractère qui servira de proposition pour trouver la combinaison mystère
	 * @return proposition entré par l'humain ou calculé par l'ordi selon qui est le joueur
	 */
	public String envoieProp() {
		String prop = "";
		if (e.toString().equals("humain")) {
			System.out.println("Veuillez entrer votre proposition");
			prop = sc.nextLine();
		}else {
			if(derProp == null) {
				for (int i = 0; i < nbChiffre; i++)
					prop = prop + "5";
			} else {
				for (int i =0; i < nbChiffre; i++) {
					char r = derRep.charAt(i);
					int p = derProp.charAt(i);
					if(p == '5') { //On vient tester toutes les combinaisons possibles. Trouve la bonne reponse en 4 tours. Faire borne
						if (r =='=') {
							prop = prop + '5';
						}else if (r == '+') {
							prop = prop + '8';
						} else {
							prop = prop + '2';
						}
					}
					if(p == '2') {
						if (r =='=') {
							prop = prop + '2';
						}else if (r == '+') {
							prop = prop + '3';
						} else {
							prop = prop + '1';
						}
					}
					if(p == '8') {
						if (r =='=') {
							prop = prop + '8';
						}else if (r == '+') {
							prop = prop + '9';
						} else {
							prop = prop + '7';
						}
					}
					if(p == '3') {
						if (r =='=') {
							prop = prop + '3';
						}else if (r == '+') {
							prop = prop + '4';
						} else {
							prop = prop + '2';
						}
					}
					if(p == '7') {
						if (r =='=') {
							prop = prop + '7';
						}else if (r == '+') {
							prop = prop + '8';
						} else {
							prop = prop + '6';
						}
					}
					if(p == '1') {
						if (r =='=') {
							prop = prop + '1';
						}else if (r == '+') {
							prop = prop + '2';
						} else {
							prop = prop + '0';
						}
					}
					if(p == '9') {
						if (r =='=') {
							prop = prop + '9';
						} else {
							prop = prop + '8';
						}
					}

				}
			}
		}
		derProp = prop;
		return prop;
	}


	public void setDerRep(String derRep) {
		this.derRep = derRep;
	}
	
	
}
