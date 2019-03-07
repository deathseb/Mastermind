package fr.rasen.mastermind.JeuPm;

import java.util.Scanner;

public class Joueur {

	private Entite e;
	private String derProp;
	private String derRep;
	private int nbChiffre;
	private Scanner sc = new Scanner(System.in);

	public Joueur(Entite e, int nbChiffre) {
		this.e = e;
		this.nbChiffre = nbChiffre;
	}

	/**
	 * Créé la proposition.
	 * @return la proposition dans un String.
	 */
	public String envoieProp() {
		String prop = "";
		boolean ok =true;
		if (e.toString().equals("humain")) {
			do {
				System.out.println("Veuillez entrer votre proposition");
				prop = sc.nextLine();
				try{
					Integer.parseInt(prop);
					if (prop.length() == nbChiffre){
						ok = true;
					}
				} catch( Exception e){
					System.out.println("Veuillez entrez uniquement " + nbChiffre + " chiffres");
					ok = false;
				}
			} while(!ok);

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
