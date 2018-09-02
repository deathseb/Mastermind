package fr.rasen.mastermind.JeuPm;

public class Plateau {

	private GameMaster gm;
	private Joueur j;
	private int nbTours = 10;
	private int nbChiffre = 4;

	/**
	 * Class gérant toute la partie permettant de faire fonctionner les différents modes de jeu
	 * @param partie
	 */
	public Plateau(String partie) {
		if (partie.equals("chall")) {
			gm = new GameMaster(Entite.ORDI, nbChiffre);
			j = new Joueur(Entite.HUMAIN);
			challenger();
		} else if (partie.equals("def")) {
			gm = new GameMaster(Entite.HUMAIN, nbChiffre);
			j = new Joueur(Entite.ORDI);
			defenseur();
		}
	}
	
	
	/**
	 * méthode correspondant au mode de jeu challengeur
	 */
	public void challenger() { //lance le mode challenger
		System.out.println(gm.getCombinaison());
	}
	
	public void defenseur() { // lance le mode défenseur
		
	}
	
	public void duel () {
		
	}
}

