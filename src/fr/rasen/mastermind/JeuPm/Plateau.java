package fr.rasen.mastermind.JeuPm;

public class Plateau {

	private GameMaster gmChall;
	private Joueur jChall;
	private GameMaster gmDef;
	private Joueur jDef;

	private int nbToursMax = 12;
	private int nbChiffre = 4;
	private String egalFinal = "";
	private boolean victoire = false;
	private int tourActuel = 0;
	Tours t = new Tours();
	Propriete p = new Propriete();
	
	
	public Plateau() {
		
	}

	/**
	 * Création pour les modes de jeu challenger et défenseur
	 * @param partie Défini le mode de jeu sélectionné par l'utilisateur
	 * @param p Fichier propriété
	 */
	public Plateau(String partie) {
		nbChiffre = p.getNbChiffre();
		nbToursMax = p.getNbTours();
		for (int i=0; i<nbChiffre; i++) {
			egalFinal = egalFinal + "=";
		}


		switch (partie) {
		case "chall": //Mode challenger
			gmChall = new GameMaster(Entite.ORDI, nbChiffre);
			jChall = new Joueur(Entite.HUMAIN, nbChiffre);
			/*while (nbToursMax != tourActuel && !victoire) {
				tourActuel++;
				String str = challenger(gmChall, jChall);
				if(str.equals(egalFinal))
					victoire = true;
			}
			if (victoire)
				System.out.println("Bravo! Vous avez trouvez la combinaison");
			else 
				System.out.println("Dommage, vous n'avez pas réussi à trouver la combinaison qui était " + gmChall.getCombinaison());*/
			break;


		case "def": // Mode défenseur
			gmDef = new GameMaster(Entite.HUMAIN, nbChiffre);
			jDef = new Joueur(Entite.ORDI, nbChiffre);
			/*while (nbToursMax != tourActuel && !victoire) {
				tourActuel++;
				String str = defenseur(gmDef, jDef);
				if(str.equals(egalFinal))
					victoire = true;
			}
			if ( victoire)
				System.out.println("Dommage, l'ordinateur à trouvé la combinaison");
			else 
				System.out.println("Bravo, vous avez gagné!");*/
			break;
		}
	}

	public int getNbToursMax() {
		return nbToursMax;
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
			gmChall = new GameMaster(Entite.ORDI, nbChiffre);
			jChall = new Joueur(Entite.HUMAIN, nbChiffre);			
			break;

		case "def": // Partie défenseur du mode duel
			gmDef = new GameMaster(Entite.HUMAIN, nbChiffre);
			jDef = new Joueur(Entite.ORDI, nbChiffre);
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
	
	public String challenger(String text) {
		return gmChall.evalProp(text);
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
	
	public String defenseur() {
		return jDef.envoieProp();
	}
	

	public GameMaster getGmChall() {
		return gmChall;
	}

	public void setGmChall(GameMaster gmChall) {
		this.gmChall = gmChall;
	}

	public Joueur getjChall() {
		return jChall;
	}

	public void setjChall(Joueur jChall) {
		this.jChall = jChall;
	}

	public GameMaster getGmDef() {
		return gmDef;
	}

	public void setGmDef(GameMaster gmDef) {
		this.gmDef = gmDef;
	}

	public Joueur getjDef() {
		return jDef;
	}

	public void setjDef(Joueur jDef) {
		this.jDef = jDef;
	}

	public void setTourActuel(int tourActuel) {
		this.tourActuel = tourActuel;
	}

	public int getNbChiffre() {
		return nbChiffre;
	}

	public String getEgalFinal() {
		return egalFinal;
	}

	public void setEgalFinal(String egalFinal) {
		this.egalFinal = egalFinal;
	}

	public boolean isVictoire() {
		return victoire;
	}

	public void setVictoire(boolean victoire) {
		this.victoire = victoire;
	}

	public Tours getT() {
		return t;
	}

	public void setT(Tours t) {
		this.t = t;
	}

	public Propriete getP() {
		return p;
	}

	public void setP(Propriete p) {
		this.p = p;
	}

	public int getTourActuel() {
		return tourActuel;
	}

	public void setNbToursMax(int nbToursMax) {
		this.nbToursMax = nbToursMax;
	}

	public void setNbChiffre(int nbChiffre) {
		this.nbChiffre = nbChiffre;
	}

	


}

