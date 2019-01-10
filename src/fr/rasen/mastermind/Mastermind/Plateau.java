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
    private boolean defaite = false;
    private int tourActuel = 0;

    public Plateau (String mode){
        this.nbToursMax = p.getNbTours();
        this.nbChiffre = p.getNbChiffre();

        switch (mode){
            case "chall":
                gmChall = new GameMaster(Entite.ORDI, nbChiffre);
                jChall = new Joueur(Entite.HUMAIN, nbChiffre);
                victoire = false;
                defaite = false;
                tourActuel =0;
                for(int j = 0; j < nbChiffre; j++){
                    egalFinal = egalFinal + "N";
                }
                while(!victoire && ! defaite){
                    challenger();
                    tourActuel = tourActuel +1;
                }
                break;
            case "def":
                gmDef = new GameMaster(Entite.HUMAIN, nbChiffre);
                jDef = new Joueur(Entite.ORDI, nbChiffre);
                victoire = false;
                defaite = false;
                tourActuel = 0;
                for(int i =0; i<nbChiffre; i++){
                    egalFinal = egalFinal + "N";
                }
                while(!victoire && !defaite){
                    defenseur();
                    tourActuel++;
                }
                break;
            case "duel":
                gmDef = new GameMaster(Entite.HUMAIN, nbChiffre);
                jDef = new Joueur(Entite.ORDI, nbChiffre);
                gmChall = new GameMaster(Entite.HUMAIN, nbChiffre);
                jChall = new Joueur(Entite.ORDI, nbChiffre);
                victoire = false;
                defaite = false;
                tourActuel =0;
                for(int i = 0; i<nbChiffre; i++){
                    egalFinal = egalFinal + "N";
                }
                for (int j =0; j < nbToursMax; j++){
                    challenger();
                    defenseur();
                    tourActuel++;
                }
        }
    }



    public Plateau(){
        gmChall = new GameMaster(Entite.ORDI, nbChiffre);
    }

    public void gagne(){
        System.out.println("Bravo, vous avez trouvé la combinaison !!");
        victoire = true;
    }

    public void perdu(){
        System.out.println("Dommage, vous n'avez pas trouvé la combinaison dans le nombre de tours. N'hésitez pas à réeassyer !!");
        defaite = true;
    }

    public void challenger(){
        if(p.isModeDev()){
            System.out.println("Combinaison = " + gmChall.showCombi());
        }
        String str = gmChall.evalProp(jChall.envoieProp());
        System.out.println(str);
        if(str.equals(egalFinal)){
            gagne();
        }
        if(tourActuel == nbToursMax){
            perdu();
        }
    }

    public void defenseur(){
       // System.out.println("Combinaison = " + gmDef.showCombi());
        String str = jDef.envoieProp();
        System.out.println(str);
        String rep = gmDef.evalProp(str);
        jDef.setDerRep(rep);
        jDef.setAllColor(rep);
        if(rep.equals(egalFinal)){
            perdu();
        }
        if(tourActuel == nbToursMax){
            gagne();
        }
    }

    public boolean isVictoire() {
        return victoire;
    }

    public void setVictoire(boolean victoire) {
        this.victoire = victoire;
    }

    public boolean isDefaite() {
        return defaite;
    }

    public void setDefaite(boolean defaite) {
        this.defaite = defaite;
    }

    public GameMaster getGmChall() {
        return gmChall;
    }

    public void setGmChall(GameMaster gmChall) {
        this.gmChall = gmChall;
    }

    public GameMaster getGmDef() {
        return gmDef;
    }

    public void setGmDef(GameMaster gmDef) {
        this.gmDef = gmDef;
    }

    public Joueur getjChall() {
        return jChall;
    }

    public void setjChall(Joueur jChall) {
        this.jChall = jChall;
    }

    public Joueur getjDef() {
        return jDef;
    }

    public void setjDef(Joueur jDef) {
        this.jDef = jDef;
    }

    public Propriete getP() {
        return p;
    }

    public void setP(Propriete p) {
        this.p = p;
    }

    public int getNbToursMax() {
        return nbToursMax;
    }

    public void setNbToursMax(int nbToursMax) {
        this.nbToursMax = nbToursMax;
    }

    public int getNbChiffre() {
        return nbChiffre;
    }

    public void setNbChiffre(int nbChiffre) {
        this.nbChiffre = nbChiffre;
    }

    public String getEgalFinal() {
        return egalFinal;
    }

    public void setEgalFinal(String egalFinal) {
        this.egalFinal = egalFinal;
    }

    public int getTourActuel() {
        return tourActuel;
    }

    public void setTourActuel(int tourActuel) {
        this.tourActuel = tourActuel;
    }
}
