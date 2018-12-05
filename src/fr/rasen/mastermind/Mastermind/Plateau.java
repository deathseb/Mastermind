package fr.rasen.mastermind.Mastermind;

public class Plateau {

    private GameMaster gmChall;
    private GameMaster gmDef;
    private Joueur jChall;
    private Joueur jDef;
    private Propriete p = new Propriete();
    private Tours tours = new Tours();

    private int nbToursMax;
    private int nbChiffre;
    private String egalFinal = "";
    private boolean victoire = false;
    private int tourActuel = 0;

    public Plateau (String mode){
        this.nbToursMax = p.getNbTours();
        this.nbChiffre = p.getNbChiffre();

        switch (mode){
            case "chall":
                gmChall = new GameMaster(Entite.ORDI, nbChiffre);
                jChall = new Joueur(Entite.HUMAIN, nbChiffre);
                break;
            case "def":
                gmDef = new GameMaster(Entite.HUMAIN, nbChiffre);
                jDef = new Joueur(Entite.ORDI, nbChiffre);
                break;
        }
    }

    public void challenger(){

    }
}
