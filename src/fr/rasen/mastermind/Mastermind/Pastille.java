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

    public Pastille(String v) {
        valeur = v;
        switch (v) {
            case "0":
                fichier = new File("src/images/Bleu.png");

            case "1":
                fichier = new File("src/images/Bleu_Clair.png");

            case "2":
                fichier = new File("src/images/Gris.png");

            case "3":
                fichier = new File("src/images/Jaune.png");

            case "4":
                fichier = new File("src/images/Marron.png");

            case "5":
                fichier = new File("src/images/Orange.png");

            case "6":
                fichier = new File("src/images/Rose.png");

            case "7":
                fichier = new File("src/images/Rouge.png");

            case "8":
                fichier = new File("src/images/Vert.png");

            case "9":
                fichier = new File("src/images/Violet.png");

            case "B":
                fichier = new File("src/images/Blanc.png");

            case "N":
                fichier = new File("src/images/Noir.png");
        }
    }

    Pastille(String v, String s) {
        valeur = v;
        fichier = new File(s);
    }

    public String getValeur() {
        return valeur;
    }

    public String nextPastille(String s) {
        switch (s) {
            case "0":
                return "1";

            case "1":
                return "2";

            case "2":
                return "3";

            case "3":
                return "4";

            case "4":
                return "5";

            case "5":
                return "6";

            case "6":
                return "7";

            case "7":
                return "8";

            case "8":
                return "9";

            default :
                return "0";
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
