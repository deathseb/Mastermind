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
	private Tours t = new Tours();
	private Propriete p = new Propriete();
	
	
	public Plateau() {
		
	}

	/**
	 * Création plateau
	 * @param partie permet de connaitre le mode de jeu à démarrer.
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
	 * Création plateau pour l'IHM.
	 * @param partie
	 * @param p
	 * @param tour
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
	 * Lance le mode challenger dans la console.
	 * @param gm
	 * @param j
	 * @return
	 */
	public String challenger(GameMaster gm, Joueur j) {
		t.setProp(j.envoieProp());
		t.setIndication(gm.evalProp(t.getProp()));
		t.setTour(tourActuel);
		System.out.println(t.toString());
		return t.getIndication();
	}

	/**
	 * Lance le mode challenger pour l'IHM.
	 * @param text proposition faite par le joueur
	 * @return
	 */
	public String challenger(String text) {
		return gmChall.evalProp(text);
	}

	/**
	 * Lance le mode défenseur dans la console.
	 * @param gm
	 * @param j
	 * @return
	 */
	public String defenseur(GameMaster gm, Joueur j) {
		t.setProp(j.envoieProp());
		System.out.println(t.getProp());
		t.setIndication(gm.evalProp(t.getProp()));
		t.setTour(tourActuel);
		System.out.println(t.toString());
		j.setDerRep(t.getIndication());
		return t.getIndication();
	}

	/**
	 * Lance le mode défenseur dans l'IHM.
	 * @return
	 */
	public String defenseur() {
		return jDef.envoieProp();
	}

	public GameMaster getGmChall() {
		return gmChall;
	}

	public Joueur getjChall() {
		return jChall;
	}

	public GameMaster getGmDef() {
		return gmDef;
	}

	public Joueur getjDef() {
		return jDef;
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

	public Propriete getP() {
		return p;
	}

	public void setP(Propriete p) {
		this.p = p;
	}

}

