package fr.rasen.mastermind.JeuPm;

public class Joueur {

	private Entite e;
	private String derProp;
	private String derRep;
	
	
	/**
	 * création du joueur, càd celui qui fera les proposition et cherchera la combinaison mystère
	 * @param e
	 */
	public Joueur(Entite e) {
		this.e = e;
	}
	
	
	/**
	 * Méthode permettant de récupérer la chaine de caractère qui servira de proposition pour trouver la combinaison mystère
	 * @return proposition entré par l'humain ou calculé par l'ordi selon qui est le joueur
	 */
	public String envoieProp() {
		String proposition = "";
		derProp = proposition;
		return proposition;
	}
}
