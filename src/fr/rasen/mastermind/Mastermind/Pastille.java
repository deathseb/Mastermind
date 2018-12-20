package fr.rasen.mastermind.Mastermind;

import java.io.File;

public enum Pastille {

    BLANC("B", "src/images/Blanc.png"),
    BLEU("0", "src/images/Bleu.png"),
    BLEU_CLAIR("1", "src/images/Bleu_Clair.png"),
    GRIS("2", "src/images/Gris.png"),
    JAUNE("3", "src/images/Jaune.png"),
    MARRON("4", "src/images/Marron.png"),
    NOIR("N", "src/images/Noir.png"),
    ORANGE("5", "src/images/Orange.png"),
    ROSE("6", "src/images/Rose.png"),
    ROUGE("7", "src/images/Rouge.png"),
    VERT("8", "src/images/Vert.png"),
    VIOLET("9", "src/images/Violet.png");

    private String valeur;
    private File fichier;

    Pastille(String v, String s) {
        valeur = v;
        fichier = new File(s);
    }

    public String getValeur() {
        return valeur;
    }

    public Pastille nextPastille(String s) {
        switch (s) {
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

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public File getFichier() {
        return fichier;
    }

    public void setFichier(File f) {
        this.fichier = f;
    }
}
