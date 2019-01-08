package fr.rasen.mastermind.Mastermind;

public class Bille {
    private Pastille pastille;

    public Bille (Pastille p){
        pastille = p;
    }


    public Bille(String v){
        switch (v) {
            case "0":
                pastille = Pastille.BLEU;

            case "1":
                pastille = Pastille.BLEU_CLAIR;

            case "2":
                pastille = Pastille.GRIS;

            case "3":
                pastille = Pastille.JAUNE;

            case "4":
                pastille = Pastille.MARRON;

            case "5":
                pastille = Pastille.ORANGE;

            case "6":
                pastille = Pastille.ROSE;

            case "7":
                pastille = Pastille.ROUGE;

            case "8":
                pastille = Pastille.VERT;

            case "9":
                pastille = Pastille.VIOLET;

            case "B":
                pastille = Pastille.BLANC;

            case "N":
                pastille = Pastille.NOIR;
        }
    }

    public Pastille nextPastille() {
        switch (pastille.getValeur()) {
            case "0":
                return Pastille.BLEU_CLAIR;

            case "1":
                return Pastille.GRIS;

            case "2":
                return Pastille.JAUNE;

            case "3":
                return Pastille.MARRON;

            case "4":
                return Pastille.ORANGE;

            case "5":
                return Pastille.ROSE;

            case "6":
                return Pastille.ROUGE;

            case "7":
                return Pastille.VERT;

            case "8":
                return Pastille.VIOLET;

            default :
                return Pastille.BLEU;
        }
    }

    public Pastille getPastille() {
        return pastille;
    }

    public void setPastille(Pastille pastille) {
        this.pastille = pastille;
    }
}
