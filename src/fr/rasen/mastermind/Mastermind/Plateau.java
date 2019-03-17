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

    /**
     * Créer le jeu en mode console.
     * @param mode Permet de savoir le mode de jeu souhaité.
     */
    public Plateau (String mode){
        this.nbToursMax = p.getNbTours();
        this.nbChiffre = p.getNbChiffre();

        switch (mode){
            case "chall":
                gmChall = new GameMaster(Entite.ORDI, nbChiffre, p.getNbCouleursMax());
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
                gmDef = new GameMaster(Entite.HUMAIN, nbChiffre, p.getNbCouleursMax());
                gmDef.creerCombi();
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
                gmDef = new GameMaster(Entite.HUMAIN, nbChiffre, p.getNbCouleursMax());
                jDef = new Joueur(Entite.ORDI, nbChiffre);
                gmChall = new GameMaster(Entite.HUMAIN, nbChiffre, p.getNbCouleursMax());
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


    /**
     * Créer pour jouer avec l'IHM.
     */
    public Plateau(){
        this.nbToursMax = p.getNbTours();
        this.nbChiffre = p.getNbChiffre();
        gmChall = new GameMaster(Entite.ORDI, nbChiffre, p.getNbCouleursMax());
        jChall = new Joueur(Entite.HUMAIN, nbChiffre);
        gmDef = new GameMaster(Entite.HUMAIN, nbChiffre, p.getNbCouleursMax());
        jDef = new Joueur(Entite.ORDI, nbChiffre);
    }

    public void gagne(){
        System.out.println("Bravo, vous avez trouvé la combinaison !!");
        victoire = true;
    }

    public void perdu(){
        System.out.println("Dommage, vous n'avez pas trouvé la combinaison dans le nombre de tours. N'hésitez pas à réeassyer !!");
        defaite = true;
    }

    /**
     * Mode challenger en IHM.
     * @param prop
     * @return
     */
    public String challenger(String prop){
        String str = gmChall.evalProp(prop);
        return str;
    }

    /**
     * Mode défenseur en IHM.
     * @param rep
     * @return
     */
    public String defenseur(String rep){
        jDef.setDerRep(rep);
        if(rep != null && !jDef.isAllColor()){
            jDef.setAllColor(rep);
        }
        return jDef.envoieProp();
    }

    /**
     * Mode challenger en console.
     */
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

    /**
     * Mode défenseur en console.
     */
    public void defenseur(){
        //System.out.println("Combinaison = " + gmDef.showCombi());
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

    public GameMaster getGmChall() {
        return gmChall;
    }

    public GameMaster getGmDef() {
        return gmDef;
    }

    public Joueur getjDef() {
        return jDef;
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

    public int getNbChiffre() {
        return nbChiffre;
    }

    public int getTourActuel() {
        return tourActuel;
    }

    public void setTourActuel(int tourActuel) {
        this.tourActuel = tourActuel;
    }
}
