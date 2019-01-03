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
                for(int i =0; i<nbChiffre; i++){
                    egalFinal = egalFinal + "N";
                }
                while(!victoire && !defaite){
                    defenseur();
                    tourActuel++;
                }
                break;
        }
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
}
