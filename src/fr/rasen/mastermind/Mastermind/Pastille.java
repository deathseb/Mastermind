package fr.rasen.mastermind.Mastermind;

public enum Pastille {

    BLANC("B", "ressources/images/Blanc.png"),
    BLEU("0", "ressources/images/Bleu.png"),
    BLEU_CLAIR("1", "ressources/images/Bleu_Clair.png"),
    GRIS("2", "ressources/images/Gris.png"),
    JAUNE("3", "ressources/images/Jaune.png"),
    MARRON("4", "ressources/images/Marron.png"),
    NOIR("N", "ressources/images/Noir.png"),
    ORANGE("5", "ressources/images/Orange.png"),
    ROSE("6", "ressources/images/Rose.png"),
    ROUGE("7", "ressources/images/Rouge.png"),
    VERT("8", "ressources/images/Vert.png"),
    VIOLET("9", "ressources/images/Violet.png");

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
