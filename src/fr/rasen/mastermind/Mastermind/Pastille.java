package fr.rasen.mastermind.Mastermind;

import java.io.File;

public enum Pastille {

    BLANC(10, "src/images/Blanc.png"),
    BLEU(0, "src/images/Bleu.png"),
    BLEU_CLAIR(1, "src/images/Bleu_Clair.png"),
    GRIS(2, "src/images/Gris.png"),
    JAUNE(3, "src/images/Jaune.png"),
    MARRON(4, "src/images/Marron.png"),
    NOIR(11, "src/images/Noir.png"),
    ORANGE(5, "src/images/Orange.png"),
    ROSE(6, "src/images/Rose.png"),
    ROUGE(7, "src/images/Rouge.png"),
    VERT(8, "src/images/Vert.png"),
    VIOLET(9, "src/images/Violet.png");

    private int valeur;
    private File fichier;


    Pastille(int v, String s) {
        valeur = v;
        fichier = new File(s);
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public File getFichier() {
        return fichier;
    }

    public void setFichier(File f) {
        this.fichier = f;
    }
}
