package fr.rasen.mastermind.JeuPm;

public class Plateau {

	private GameMaster gm;
	private Joueur j;
	private int nbToursMax = 10;
	private int nbChiffre = 4;
	private String egalFinal = "";
	private boolean victoire = false;
	private int tourActuel = 0;
	Tours t = new Tours();

	/**
	 * Création pour les modes de jeu challenger et défenseur
	 * @param partie Défini le mode de jeu sélectionné par l'utilisateur
	 * @param p Fichier propriété
	 */
	public Plateau(String partie, Propriete p) {
		nbChiffre = p.getNbChiffre();
		nbToursMax = p.getNbTours();
		for (int i=0; i<nbChiffre; i++) {
			egalFinal = egalFinal + "=";
		}


		switch (partie) {
		case "chall": //Mode challenger
			gm = new GameMaster(Entite.ORDI, nbChiffre);
			j = new Joueur(Entite.HUMAIN, nbChiffre);
			while (nbToursMax != tourActuel && !victoire) {
				tourActuel++;
				String str = challenger(gm, j);
				if(str.equals(egalFinal))
					victoire = true;
			}
			if (victoire)
				System.out.println("Bravo! Vous avez trouvez la combinaison");
			else 
				System.out.println("Dommage, vous n'avez pas réussi à trouver la combinaison qui était " + gm.getCombinaison());
			break;


		case "def": // Mode défenseur
			gm = new GameMaster(Entite.HUMAIN, nbChiffre);
			j = new Joueur(Entite.ORDI, nbChiffre);
			while (nbToursMax != tourActuel && !victoire) {
				tourActuel++;
				String str = defenseur(gm, j);
				if(str.equals(egalFinal))
					victoire = true;
			}
			if ( victoire)
				System.out.println("Dommage, l'ordinateur à trouvé la combinaison");
			else 
				System.out.println("Bravo, vous avez gagné!");
			break;
		}
	}

	/**
	 * Création pour le mode duel
	 * @param partie Défini le rôle de cette objet dans le mode duel
	 * @param p Fichier propriété
	 * @param tour Tour actuel
	 */
	public Plateau (String partie, Propriete p, int tour) {
		tourActuel = tour;
		nbChiffre = p.getNbChiffre();
		nbToursMax = p.getNbTours();
		for (int i=0; i<nbChiffre; i++) {
			egalFinal = egalFinal + "=";
		}

		switch (partie) {
		case "chall": //Partie challenger du mode duel
			gm = new GameMaster(Entite.ORDI, nbChiffre);
			j = new Joueur(Entite.HUMAIN, nbChiffre);			
			break;

		case "def": // Partie défenseur du mode duel
			gm = new GameMaster(Entite.HUMAIN, nbChiffre);
			j = new Joueur(Entite.ORDI, nbChiffre);
			break;
		}
	}


	/**
	 * Méthode déroulant la partie en mode challenger
	 * @param gm Donne le bon GameMaster pour le mode duel
	 * @param j Donne le bon Joueur pour le mode duel
	 * @return le String permettant d'évaluer une victoire
	 */
	public String challenger(GameMaster gm, Joueur j) { //lance le mode challenger
		t.setProp(j.envoieProp());
		t.setIndication(gm.evalProp(t.getProp()));
		t.setTour(tourActuel);
		System.out.println(t.toString());
		return t.getIndication();
	}

	/**
	 * Méthode déroulant la partie en mode défenseur
	 * @param gm Donne le bon GameMaster pour le mode duel
	 * @param j Donne le bon Joueur pour le mode duel
	 * @return le String permettant de savoir si il y a victoire
	 */
	public String defenseur(GameMaster gm, Joueur j) { // lance le mode défenseur
		t.setProp(j.envoieProp());
		System.out.println(t.getProp());
		t.setIndication(gm.evalProp(t.getProp()));
		t.setTour(tourActuel);
		System.out.println(t.toString());
		j.setDerRep(t.getIndication());
		return t.getIndication();
	}

	public GameMaster getGm() {
		return gm;
	}

	public void setGm(GameMaster gm) {
		this.gm = gm;
	}

	public Joueur getJ() {
		return j;
	}

	public void setJ(Joueur j) {
		this.j = j;
	}

	public void setTourActuel(int tourActuel) {
		this.tourActuel = tourActuel;
	}



}

