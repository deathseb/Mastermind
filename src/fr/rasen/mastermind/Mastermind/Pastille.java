package fr.rasen.mastermind.Mastermind;

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
    private String fichier;

    Pastille(String v, String s) {
        valeur = v;
        fichier = s;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String f) {
        this.fichier = f;
    }
}
