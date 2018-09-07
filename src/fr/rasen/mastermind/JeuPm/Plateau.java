package fr.rasen.mastermind.JeuPm;

public class Plateau {

	private GameMaster gm;
	private Joueur j;
	private int nbTours = 10;
	private int nbChiffre = 4;
	private String egalFinal = "";
	private boolean victoire = false;

	/**
	 * Class gérant toute la partie permettant de faire fonctionner les différents modes de jeu
	 * @param partie
	 */
	public Plateau(String partie, Propriete p) {
		for (int i=0; i<nbChiffre; i++) {
			egalFinal = egalFinal + "=";
		}
		nbTours = p.getNbTours();
		nbChiffre = p.getNbChiffre();
		switch (partie) {
		case "chall": //Mode challenger
			gm = new GameMaster(Entite.ORDI, nbChiffre);
			j = new Joueur(Entite.HUMAIN, nbChiffre);
			while (nbTours != 0 && !victoire) {
				String str = challenger(gm, j);
				if(str.equals(egalFinal))
					victoire = true;
				nbTours--;
			}
			if ( victoire)
				System.out.println("Bravo! Vous avez trouvez la combinaison");
			else 
				System.out.println("Dommage, vous n'avez pas réussi à trouver la combinaison qui était " + gm.getCombinaison());
			
		case "def":
			gm = new GameMaster(Entite.HUMAIN, nbChiffre);
			j = new Joueur(Entite.ORDI, nbChiffre);
			while (nbTours != 0 && !victoire) {
				String str = defenseur(gm, j);
				if(str.equals(egalFinal))
					victoire = true;
			}
			if ( victoire)
				System.out.println("Dommage, l'ordinateur à trouvé la combinaison");
			else 
				System.out.println("Bravo, vous avez gagné!");
		}
	}


	/**
	 * Méthode déroulant la partie en mode challenger
	 * @param gm Donne le bon GameMaster pour le mode duel
	 * @param j Donne le bon Joueur pour le mode duel
	 * @return le String permettant d'évaluer une victoire
	 */
	public String challenger(GameMaster gm, Joueur j) { //lance le mode challenger
		String str = gm.evalProp(j.envoieProp());
		System.out.println(str);
		return str;
	}

	/**
	 * Méthode déroulant la partie en mode défenseur
	 * @param gm Donne le bon GameMaster pour le mode duel
	 * @param j Donne le bon Joueur pour le mode duel
	 * @return le String permettant de savoir si il y a victoire
	 */
	public String defenseur(GameMaster gm, Joueur j) { // lance le mode défenseur
		String prop = j.envoieProp();
		System.out.println(prop);
		String str = gm.evalProp(prop);
		j.setDerRep(str);
		return str;
	}

}

