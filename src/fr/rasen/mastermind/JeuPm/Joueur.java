package fr.rasen.mastermind.JeuPm;

public class Joueur {

	private Entite e;
	private String derProp;
	private String derRep;
	
	public Joueur(Entite e) {
		this.e = e;
	}
	
	public String envoieProp() {
		String proposition = "";
		derProp = proposition;
		return proposition;
	}
}
